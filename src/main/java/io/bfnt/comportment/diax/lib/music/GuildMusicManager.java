package io.bfnt.comportment.diax.lib.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

/**
 * Created by Comporment on 02/04/2017 at 16:18
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class GuildMusicManager
{
    public final AudioPlayer player;
    public final TrackScheduler scheduler;

    /**
     * Constructor which wraps a {@link AudioPlayerManager} to make a {@link GuildMusicManager}
     *
     * @param manager The {@link GuildMusicManager} which was created.
     * @since Azote
     */
    public GuildMusicManager(AudioPlayerManager manager)
    {
        player = manager.createPlayer();
        scheduler = new TrackScheduler(player);
        player.addListener(scheduler);
    }

    /**
     * Method which wraps the {@link AudioPlayer} to create a {@link AudioPlayerSendHandler}
     *
     * @return The {@link AudioPlayerSendHandler} which was created.
     * @since Azote
     */
    public AudioPlayerSendHandler getSendHandler()
    {
        return new AudioPlayerSendHandler(player);
    }
}
