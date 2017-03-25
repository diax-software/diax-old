package io.bfnt.comportment.diax.api.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

/**
 * Created by Comporment on 25/03/2017 at 22:12
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class GuildMusicManager
{
    public final AudioPlayer player;
    public final TrackScheduler scheduler;
    public GuildMusicManager(AudioPlayerManager manager)
    {
        player = manager.createPlayer();
        scheduler = new TrackScheduler(player);
        player.addListener(scheduler);
    }
    public AudioPlayerSendHandler getSendHandler()
    {
        return new AudioPlayerSendHandler(player);
    }
}
