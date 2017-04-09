package me.diax.bot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.diax.bot.lib.audio.DiaxAudioTrack;
import me.diax.bot.lib.audio.DiaxGuildMusicManager;
import me.diax.bot.lib.audio.DiaxTrackScheduler;
import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comportment on 05/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"play", "queue", "search"}, minimumArgs = 1, guildOnly = true, description = "Plays the given URL/Query provided.")
public class PlayCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String truncated) {
        DiaxGuildMusicManager manager = DiaxGuildMusicManager.getManagerFor(trigger.getGuild());
        query(manager, trigger, truncated);
    }

    private void query(DiaxGuildMusicManager manager, Message message, String query) {
        manager.getPlayerManager().loadItem(query, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                message.getTextChannel().sendMessage(DiaxUtil.musicEmbed(String.format("Queuing `%s ` by `%s `.", track.getInfo().title, track.getInfo().author))).queue();
                manager.scheduler.queue(new DiaxAudioTrack(track, message.getAuthor(), message.getTextChannel()));
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                DiaxTrackScheduler scheduler = manager.scheduler;
                AudioTrack track;
                if (playlist.isSearchResult()) {
                    track = playlist.getTracks().get(0);
                    message.getTextChannel().sendMessage(DiaxUtil.musicEmbed(String.format("Queuing `%s ` by `%s `.", track.getInfo().title, track.getInfo().author))).queue();
                    scheduler.queue(new DiaxAudioTrack(track, message.getAuthor(), message.getTextChannel()));
                } else {
                    if (playlist.getSelectedTrack() != null) {
                        track = playlist.getSelectedTrack();
                        message.getTextChannel().sendMessage(DiaxUtil.musicEmbed(String.format("Queuing `%s ` by `%s `.", track.getInfo().title, track.getInfo().author))).queue();
                        scheduler.queue(new DiaxAudioTrack(playlist.getSelectedTrack(), message.getAuthor(), message.getTextChannel()));
                    } else {
                        message.getTextChannel().sendMessage(DiaxUtil.musicEmbed(String.format("Adding `%s ` tracks from the playlist `%s `.", playlist.getTracks().size(), playlist.getName()))).queue();
                        playlist.getTracks().forEach(audioTrack -> scheduler.queue(new DiaxAudioTrack(audioTrack, message.getAuthor(), message.getTextChannel())));
                    }
                }
            }

            @Override
            public void noMatches() {
                if (!query.startsWith("Search Results:")) {
                    query(manager, message, "ytsearch: " + query);
                } else {
                    message.getTextChannel().sendMessage(DiaxUtil.musicEmbed("No results found.")).queue();
                }
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                message.getTextChannel().sendMessage(DiaxUtil.musicEmbed(String.format("Failed to load `%s` because `%s`.", query, exception.getMessage()))).queue();
            }
        });
    }
}