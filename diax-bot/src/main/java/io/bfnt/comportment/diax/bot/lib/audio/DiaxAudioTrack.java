package io.bfnt.comportment.diax.bot.lib.audio;


import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

/**
 * Created by Comporment on 04/04/2017 at 20:39
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxAudioTrack {

    private AudioTrack track;
    private User requester;
    private TextChannel channel;

    public DiaxAudioTrack(AudioTrack track, User requester, TextChannel channel) {
        this.track = track;
        this.requester = requester;
        this.channel = channel;
    }

    public AudioTrack getTrack() {
        return track;
    }

    public User getRequester() {
        return requester;
    }

    public TextChannel getChannel() {
        return channel;
    }

    @Override
    public DiaxAudioTrack clone() {
        return new DiaxAudioTrack(track.makeClone(), requester, channel);
    }
}