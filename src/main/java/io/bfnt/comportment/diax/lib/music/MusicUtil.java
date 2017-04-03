package io.bfnt.comportment.diax.lib.music;

/**
 * Created by Connor on 02/04/2017.
 */

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import io.bfnt.comportment.diax.lib.Diax;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MusicUtil extends Diax {
    private static final AudioPlayerManager playerManager = defaultAudioPlayerManager();
    private static final Map<Long, GuildMusicManager> musicManagers = new HashMap<>();

    /**
     * A method to make the {@link AudioPlayerManager} which has the registered sources.
     *
     * @return A {@link DefaultAudioPlayerManager} with the registered {@link com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers}
     * @since Azote
     */
    private static DefaultAudioPlayerManager defaultAudioPlayerManager() {
        DefaultAudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerLocalSource(playerManager);
        AudioSourceManagers.registerRemoteSources(playerManager);
        return playerManager;
    }

    /**
     * Shuffles the tracks in the {@link GuildMusicManager}
     *
     * @param manager The {@link GuildMusicManager} to shuffle.
     * @since Azote
     */
    public static void shuffle(GuildMusicManager manager) {
        manager.scheduler.shuffle();
    }

    /**
     * Skips to the next track in the playlist.
     *
     * @param manager The {@link GuildMusicManager} to skip.
     * @since Azote
     */
    public static void skip(GuildMusicManager manager) {
        manager.scheduler.nextTrack(manager.getChannel());
    }

    /**
     * Method which loads and plays the track at the given url.
     *
     * @param manager  The manager of the {@link Guild}
     * @param channel  The channel the {@link net.dv8tion.jda.core.entities.Message} was sent in.
     * @param trackUrl The url of the track.
     * @since Azote
     */
    public static void loadAndPlay(GuildMusicManager manager, MessageChannel channel, String trackUrl) {
        playerManager.loadItemOrdered(manager, trackUrl, new AudioLoadResultHandler() {
            /**
             * Method that is fired when an {@link AudioTrack} is successfully loaded.
             *
             * @param track The {@link AudioTrack} that was loaded.
             * @since Azote
             */
            @Override
            public void trackLoaded(AudioTrack track) {
                AudioTrackInfo info = track.getInfo();
                channel.sendMessage(new Diax().makeEmbed().addField("Loaded!", String.format("`%s ` by `%s ` has been added to the queue `[%s]`", info.title, info.author, getTimestamp(info.length)), false).build()).queue();
                if (manager.scheduler.getQueue().isEmpty()) {
                    channel.sendMessage(new Diax().makeEmbed().addField("Now Playing!", String.format("`%s ` by `%s ` is now playing. `[%s] `", info.title, info.author, getTimestamp(info.length)), false).build()).queue();
                }
                manager.scheduler.queue(track);
            }

            /**
             * Method that is fired when a {@link AudioPlaylist} is successfully loaded.
             *
             * @param playlist The {@link AudioPlaylist} that was loaded.
             * @since Azote
             */
            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                channel.sendMessage(new Diax().makeEmbed().addField("Loaded!", String.format("The playlist `%s ` containing `%s ` tracks has been added to the queue.", playlist.getName(), playlist.getTracks().size()), false).build()).queue();
                if (manager.scheduler.getQueue().isEmpty()) {
                    AudioTrackInfo info = playlist.getTracks().get(0).getInfo();
                    channel.sendMessage(new Diax().makeEmbed().addField("Now Playing!", String.format("`%s ` by `%s ` is now playing. `[%s] `", info.title, info.author, getTimestamp(info.length)), false).build()).queue();
                }
                playlist.getTracks().forEach(manager.scheduler::queue);
            }

            /**
             * Method that is fired when no matches are found.
             *
             * @since Azote
             */
            @Override
            public void noMatches() {
                channel.sendMessage(new Diax().makeEmbed().addField("Error!", String.format("There were no matches found for `%s `", trackUrl), false).setColor(new Color(255, 0, 0)).build()).queue();
            }

            /**
             * Method that is fired when a {@link FriendlyException} is encountered when a song is loaded.
             *
             * @param exception The {@link FriendlyException} that was thrown.
             * @since Azote
             */
            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage(new Diax().makeEmbed().addField("Error!", String.format("The track could not be played due to: `%s `", exception.getMessage()), false).build()).queue();
            }
        });
    }

    /**
     * Method which gets the music manager for a guild.
     *
     * @param channel The {@link TextChannel} to get the {@link Guild} to get the {@link GuildMusicManager} for.
     * @return The {@link GuildMusicManager} for the guild- never null.
     * @since Azote
     */
    public synchronized static GuildMusicManager getMusicManager(TextChannel channel) {
        Guild guild = channel.getGuild();
        long guildId = Long.parseLong(guild.getId());
        GuildMusicManager musicManager = musicManagers.get(guildId);
        musicManagers.putIfAbsent(guildId, musicManager);
        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager, channel);
            musicManagers.put(guildId, musicManager);
        }
        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }

    /**
     * Method wrote by dv8tion to get the timestamp from the length in milliseconds.
     *
     * @return The timestamp in the format HH:mm:ss or mm:ss
     * @since Azote
     */
    private static String getTimestamp(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        if (hours > 0)
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        else
            return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Method to make the track info format when stating which {@link AudioTrack} is playing.
     *
     * @param track The {@link AudioTrack} to grab the information of.
     * @return A {@link MessageEmbed} containing the info of the {@link AudioTrack}
     */
    protected static MessageEmbed trackEmbed(AudioTrack track) {
        AudioTrackInfo info = track.getInfo();
        return new Diax().makeEmbed().addField("Now Playing...", String.format("`%s ` by `%s ` `[%s] `", info.title, info.author, MusicUtil.getTimestamp(info.length)), false).build();
    }
}
