package io.bfnt.comportment.diax.lib.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Comporment on 04/04/2017 at 20:50
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxTrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<DiaxAudioTrack> queue;
    private TextChannel channel;
    private boolean repeating;

    public DiaxTrackScheduler(AudioPlayer player, TextChannel channel) {
        this.channel = channel;
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    public AudioPlayer getPlayer() {
        return this.player;
    }

    public BlockingQueue<DiaxAudioTrack> getQueue() {
        return queue;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public void setChannel(TextChannel channel) {
        this.channel = channel;
    }

    public void clear() {
        queue.clear();
        player.startTrack(null, false);
    }

    public void queue(DiaxAudioTrack track) {
        if (!player.startTrack(track.getTrack(), true)) queue.offer(track);
    }

    public void shuffle() {
        List<DiaxAudioTrack> tracks = new ArrayList<>();
        queue.drainTo(tracks);
        Collections.shuffle(tracks);
        queue.addAll(tracks);
    }

    public boolean skip() {
        DiaxAudioTrack track = this.queue.poll();
        AudioTrackInfo info = track.getTrack().getInfo();
        User requester = track.getRequester();
        this.channel.sendMessage(String.format("Now playing: `%s ` by `%s ` | Requested by: `%s#%s `", info.title, info.author, requester.getName(), requester.getDiscriminator()));
        return player.startTrack(queue.poll().getTrack(), false);
    }

    public void setRepeating(boolean repeating) {
        String message = "The queue is no longer repeating.";
        if (repeating) message = "The queue is now repeating.";
        this.repeating = repeating;
        channel.sendMessage(message);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason reason) {
        if (this.repeating) {
            player.startTrack(track.makeClone(), false);
            this.channel.sendMessage("Repeating the last song.").queue();
            return;
        }
        if (this.queue.size() <= 0) {
            this.channel.getGuild().getAudioManager().closeAudioConnection();
            this.channel.sendMessage("Queue concluded.").queue();
            return;
        }
        skip();
    }
}