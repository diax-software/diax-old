package io.bfnt.comportment.diax.api.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Comporment on 25/03/2017 at 22:50
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class Music extends DiaxCommand
{
    protected final AudioPlayerManager playerManager;
    protected final Map<Long, GuildMusicManager> musicManagers;
    public Music()
    {
        this.musicManagers = new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerLocalSource(playerManager);
        AudioSourceManagers.registerRemoteSources(playerManager);
    }
    protected synchronized GuildMusicManager getGuildAudioPlayer(Guild guild)
    {
        long guildId = Long.parseLong(guild.getId());
        GuildMusicManager musicManager = musicManagers.get(guildId);
        musicManagers.putIfAbsent(guildId, musicManager);
        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] command = event.getMessage().getContent().split(" ", 2);
        Guild guild = event.getGuild();

        if (guild != null) {
            if ("~play".equals(command[0]) && command.length == 2) {
                loadAndPlay(event.getTextChannel(), command[1]);
            } else if ("~skip".equals(command[0])) {
                skipTrack(event.getTextChannel());
            }
        }
        super.onMessageReceived(event);
    }
    protected void loadAndPlay(final TextChannel channel, final String trackUrl)
    {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler()
        {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage("Adding to queue " + track.getInfo().title).queue();

                play(channel.getGuild(), musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                channel.sendMessage("Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")").queue();

                play(channel.getGuild(), musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                channel.sendMessage("Nothing found by " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage("Could not play: " + exception.getMessage()).queue();
            }
        });
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
}