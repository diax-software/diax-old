package io.bfnt.comportment.diax.lib.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.core.entities.Guild;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Comporment on 02/04/2017 at 16:19
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class TrackScheduler extends AudioEventAdapter
{
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private final AudioTrack lastTrack = null;
    private final Guild guild;
    private boolean repeating;

    /**
     * Constructor that creates a new {@link TrackScheduler} using a {@link AudioPlayer}
     *
     * @param player The {@link AudioPlayer} to use to create a new {@link TrackScheduler}
     * @since Azote
     */
    public TrackScheduler(AudioPlayer player, Guild guild)
    {
        this.guild = guild;
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Method which attempts to play or queue the given {@link AudioTrack}.
     *
     * @param track The {@link AudioTrack} to attempt to play.
     * @since Azote
     */
    public void queue(AudioTrack track)
    {
        if (!player.startTrack(track, true)) queue.offer(track);
    }

    /**
     * Method which clears the tracks in the {@link #queue}
     *
     * @since Azote
     */
    public void clear()
    {
        queue.clear();
    }

    /**
     * Method which forces the next {@link AudioTrack} in the queue to be played.
     *
     * @since Azote
     */
    public void nextTrack()
    {
        player.startTrack(queue.poll(), false);
    }

    /**
     * Method which is fired when the {@link AudioTrack} ends. It attempts to play the next track.
     *
     * @param player The {@link AudioPlayer} playing the {@link AudioTrack}
     * @param track The {@link AudioTrack} that has ended.
     * @param endReason The {@link AudioTrackEndReason} which caused the track to end.
     * @since Azote
     */
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason)
    {
        if (queue.size() <= 0) guild.getAudioManager().closeAudioConnection();
        if (endReason.mayStartNext)
        {
            if (repeating)
            {
                player.startTrack(lastTrack.makeClone(), false);
            }
            else
            {
                nextTrack();
            }
        }
    }

    /**
     * Method that shuffles the queue.
     *
     * @since Azote
     */
    public void shuffle()
    {
        Collections.shuffle((List<?>) queue);
    }

    /**
     * Method to check is the song is repeating.
     *
     * @return A boolean saying if the {@link AudioTrack} is repeating.
     * @since Azote
     */
    public boolean isRepeating()
    {
        return repeating;
    }

    /**
     * Method to set the {@link AudioTrack} to repeating.
     *
     * @param repeating Boolean to set the new value to.
     * @since Azote
     */
    public void setRepeating(boolean repeating)
    {
        this.repeating = repeating;
    }
}