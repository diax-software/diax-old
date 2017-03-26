package io.bfnt.comportment.diax.api.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Comporment on 26/03/2017 at 11:12
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class TrackScheduler extends AudioEventAdapter
{
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    public TrackScheduler(AudioPlayer player)
    {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }
    public void queue(AudioTrack track)
    {
        if (!player.startTrack(track, true)) queue.offer(track);
    }
    public void nextTrack()
    {
        player.startTrack(queue.poll(), false);
    }
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason)
    {
        if (endReason.mayStartNext) nextTrack();
    }
}