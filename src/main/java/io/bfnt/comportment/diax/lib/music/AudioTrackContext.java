package io.bfnt.comportment.diax.lib.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

/**
 * Created by Comporment on 04/04/2017 at 07:48
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class AudioTrackContext {
    private TextChannel channel;
    private User requester;
    private AudioTrack track;

    /**
     * Method to get more context to the track that is being queued, and replaces the normal {@link AudioTrack}
     *
     * @param track The {@link AudioTrack} to be played.
     * @param requester The {@link User} who requested the {@link AudioTrack}
     * @param channel The {@link TextChannel} the {@link AudioTrack} was requested in.
     * @since Brazen
     */
    public AudioTrackContext(AudioTrack track, User requester, TextChannel channel) {
        this.track = track;
        this.requester = requester;
        this.channel = channel;
    }

    /**
     * Method to get the {@link AudioTrack} in the {@link AudioTrackContext}
     *
     * @return The {@link AudioTrack} of the {@link AudioTrackContext}
     * @since Brazen
     */
    public AudioTrack getTrack() {
        return track;
    }

    /**
     * Method to get the {@link User} who requested the {@link AudioTrack}
     *
     * @return The {@link User} who requested the {@link AudioTrack}
     * @since Brazen
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Method to get the {@link TextChannel} the {@link AudioTrack} was requested in.
     *
     * @return The {@link TextChannel} the {@link AudioTrack} was requested in.
     * @since Brazen
     */
    public TextChannel getTextChannel() {
        return channel;
    }

    /**
     * Method used to make a clone of the {@link AudioTrackContext} and {@code overrides} the method in {@link Object}
     *
     * @return A {@link AudioTrackContext} exactly the same as this one.
     * @since Brazen
     */
    @Override
    public AudioTrackContext clone() {
        return new AudioTrackContext(track.makeClone(), requester, channel);
    }
}