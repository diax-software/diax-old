package io.bfnt.comportment.diax.lib.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import io.bfnt.comportment.diax.lib.Diax;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.ArrayList;
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
    private AudioTrack lastTrack;
    private final TextChannel channel;
    private boolean repeating;

    /**
     * Constructor that creates a new {@link TrackScheduler} using a {@link AudioPlayer}
     *
     * @param player The {@link AudioPlayer} to use to create a new {@link TrackScheduler}
     * @since Azote
     */
    public TrackScheduler(AudioPlayer player, TextChannel channel)
    {
        this.channel = channel;
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }
    
    /**
     * Method which gets the {@link #queue}
     *
     * @return The {@link #queue}
     * @since Azote
     */
    public BlockingQueue<AudioTrack> getQueue()
    {
        return queue;
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
        channel.sendMessage(new Diax().makeEmbed().addField("Cleared!", "The queue has been cleared!", false).build()).queue();
    }

    /**
     * Method which forces the next {@link AudioTrack} in the queue to be played.
     *
     * @since Azote
     */
    public void nextTrack(TextChannel channel)
    {
        AudioTrack track = queue.poll();
        player.startTrack(track, false);
        channel.sendMessage(MusicUtil.trackEmbed(track)).queue();
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
        if (queue.size() <= 0) channel.getGuild().getAudioManager().closeAudioConnection();
        lastTrack = track;
        if (endReason.mayStartNext)
        {
            if (repeating)
            {
                player.startTrack(lastTrack.makeClone(), false);
                channel.sendMessage(MusicUtil.trackEmbed(lastTrack)).queue();
            }
            else
            {
                nextTrack(channel);
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
        List<AudioTrack> tracks = new ArrayList<>();
        queue.drainTo(tracks);
        Collections.shuffle(tracks);
        queue.addAll(tracks);
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
        String status = "no longer repeating.";
        if (repeating) status = "now repeating.";
        channel.sendMessage(new Diax().makeEmbed().addField("Repeating!", "The queue is " + status, false).build()).queue();
    }
}
