package io.bfnt.comportment.diax.commands.music;

/**
 * Created by Connor on 02/04/2017.
 */

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import io.bfnt.comportment.diax.lib.music.GuildMusicManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

public class MusicUtil extends ListenerAdapter
{
    private static final AudioPlayerManager playerManager = defaultAudioPlayerManager();
    private static final Map<String, GuildMusicManager> musicManagers = new HashMap<>();

    /**
     * A method to make the {@link AudioPlayerManager} which has the registered sources.
     *
     * @return A {@link DefaultAudioPlayerManager} with the registered {@link com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers}
     * @since Azote
     */
    private static DefaultAudioPlayerManager defaultAudioPlayerManager()
    {
        DefaultAudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        playerManager.registerSourceManager(new YoutubeAudioSourceManager());
        playerManager.registerSourceManager(new SoundCloudAudioSourceManager());
        playerManager.registerSourceManager(new BandcampAudioSourceManager());
        playerManager.registerSourceManager(new VimeoAudioSourceManager());
        playerManager.registerSourceManager(new TwitchStreamAudioSourceManager());
        playerManager.registerSourceManager(new HttpAudioSourceManager());
        playerManager.registerSourceManager(new LocalAudioSourceManager());
        return playerManager;
    }

    /**
     * Method which loads and plays the track at the given url.
     *
     * @param manager The manager of the {@link Guild}
     * @param channel The channel the {@link net.dv8tion.jda.core.entities.Message} was sent in.
     * @param trackUrl The url of the track.
     * @since Azote
     */
    public static void loadAndPlay(GuildMusicManager manager, MessageChannel channel, String trackUrl)
    {
        playerManager.loadItemOrdered(manager, trackUrl, new AudioLoadResultHandler()
        {
            @Override
            public void trackLoaded(AudioTrack track)
            {
                channel.sendMessage("loaded...").queue();
                manager.scheduler.queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist)
            {
                channel.sendMessage("playlist loaded...").queue();
                playlist.getTracks().forEach(manager.scheduler::queue);
            }

            @Override
            public void noMatches()
            {
                channel.sendMessage("no matches...").queue();
            }

            @Override
            public void loadFailed(FriendlyException exception)
            {
                channel.sendMessage("nope...").queue();
            }
        });
    }

    /**
     * Method which gets the music manager for a guild.
     *
     * @param guild The {@link Guild} to get the music manager for.
     * @return The {@link GuildMusicManager} for the guild- never null.
     * @since Azote
     */
    public static GuildMusicManager getMusicManager(Guild guild)
    {
        String guildId = guild.getId();
        musicManagers.putIfAbsent(guildId, new GuildMusicManager(playerManager));
        return musicManagers.get(guildId);
    }

    /**
     * Method not wrote by me- could be useful.
     *
     * @since Azote
     * @deprecated
     */
    @Deprecated
    private static String getTimestamp(long milliseconds)
    {
        int seconds = (int) (milliseconds / 1000) % 60 ;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours   = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        if (hours > 0)
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        else
            return String.format("%02d:%02d", minutes, seconds);
    }
}