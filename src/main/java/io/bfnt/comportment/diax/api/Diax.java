package io.bfnt.comportment.diax.api;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import io.bfnt.comportment.diax.api.command.ErrorType;
import io.bfnt.comportment.diax.api.music.GuildMusicManager;
import io.bfnt.comportment.diax.commands.information.Bump;
import io.bfnt.comportment.diax.commands.information.Ginfo;
import io.bfnt.comportment.diax.commands.information.Help;
import io.bfnt.comportment.diax.commands.information.WhoAmI;
import io.bfnt.comportment.diax.commands.moderation.Ban;
import io.bfnt.comportment.diax.commands.moderation.Kick;
import io.bfnt.comportment.diax.commands.moderation.Purge;
import io.bfnt.comportment.diax.commands.moderation.Softban;
import io.bfnt.comportment.diax.commands.music.Play;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.utils.PermissionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * Created by Comporment on 23/03/2017 at 16:41
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Diax extends ListenerAdapter
{
    protected MessageBuilder makeMessage(String title, String content)
    {
        return new MessageBuilder().append(String.format("__**%s**__\n\n%s", title, content));
    }
    protected void makeError(MessageChannel channel, ErrorType type)
    {
        channel.sendMessage(makeMessage("Error!", type.getDescription()).build()).queue();
    }
    @Deprecated
    protected EmbedBuilder makeEmbed(String title, String content)
    {
        return new EmbedBuilder().setTitle(String.format("__**%s**__", title), "").setDescription(content);
    }
    protected String getNiceName(User user)
    {
        return String.format("%s#%s", user.getName(), user.getDiscriminator());
    }
    protected String getNiceName(Member member)
    {
        User user = member.getUser();
        return String.format("%s#%s", user.getName(), user.getDiscriminator());
    }
    protected String prefix()
    {
        return "<>";
    }
    protected TreeSet<DiaxCommand> getCommands()
    {
        return new TreeSet<DiaxCommand>()
        {
            {
                add(new WhoAmI());
                add(new Help());
                add(new Kick());
                add(new Softban());
                add(new Ban());
                add(new Purge());
                add(new Ginfo());
                add(new Bump());
                add(new Play());
            }
        };
    }
    protected boolean checkPermission(Member member, Permission permission)
    {
        return PermissionUtil.checkPermission(member.getGuild(), member, permission);
    }
    public Diax() {
        this.musicManagers = new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }
    protected void loadAndPlay(final TextChannel channel, final String trackUrl)
    {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler()
        {
            @Override
            public void trackLoaded(AudioTrack track)
            {
                long millis = track.getDuration();
                channel.sendMessage(makeMessage("Music", String.format("Adding `%s` by `%s` to the queue. [%02d:%02d:%02d]", track.getInfo().title, track.getInfo().author, TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))).build()).queue();
                play(channel.getGuild(), musicManager, track);
            }
            @Override
            public void playlistLoaded(AudioPlaylist playlist)
            {
                AudioTrack firstTrack = playlist.getSelectedTrack();
                if (firstTrack == null) firstTrack = playlist.getTracks().get(0);
                channel.sendMessage(makeMessage("Music", String.format("Added the playlist `%s` to the queue.", playlist.getName())).build()).queue();
                long millis = firstTrack.getDuration();
                channel.sendMessage(makeMessage("Music", String.format("Adding `%s` by `%s` to the queue. [%02d:%02d:%02d]", firstTrack.getInfo().title, firstTrack.getInfo().author, TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))).build()).queue();
                play(channel.getGuild(), musicManager, firstTrack);
            }
            @Override
            public void noMatches()
            {
                channel.sendMessage(String.format("`%s` could not be found.", trackUrl)).queue();
            }
            @Override
            public void loadFailed(FriendlyException exception)
            {
                channel.sendMessage("Could not play: " + exception.getMessage()).queue();
            }
        });
    }
    protected synchronized GuildMusicManager getGuildAudioPlayer(Guild guild)
    {
        long guildId = Long.parseLong(guild.getId());
        GuildMusicManager musicManager = musicManagers.get(guildId);
        musicManagers.putIfAbsent(guildId, musicManager);
        if (musicManager == null)
        {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }
        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }
    protected void play(Guild guild, GuildMusicManager musicManager, AudioTrack track)
    {
        connectToFirstVoiceChannel(guild.getAudioManager());
        musicManager.scheduler.queue(track);
    }
    protected void skipTrack(TextChannel channel)
    {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.scheduler.nextTrack();
        channel.sendMessage("Skipped to next track.").queue();
    }
    protected static void connectToFirstVoiceChannel(AudioManager audioManager)
    {
        if (!audioManager.isConnected() && !audioManager.isAttemptingToConnect())
        {
            for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels())
            {
                audioManager.openAudioConnection(voiceChannel);
                break;
            }
        }
    }
    protected final AudioPlayerManager playerManager;
    protected final Map<Long, GuildMusicManager> musicManagers;
}