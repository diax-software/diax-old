package io.bfnt.comportment.diax.lib.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

/**
 * Created by Comporment on 02/04/2017 at 16:15
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class AudioPlayerSendHandler implements AudioSendHandler
{
    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    /**
     * Constructor which makes a {@link AudioPlayerSendHandler} using a {@link AudioPlayer}
     *
     * @param audioPlayer The {@link AudioPlayer} to wrap.
     * @since Azote
     */
    public AudioPlayerSendHandler(AudioPlayer audioPlayer)
    {
        this.audioPlayer = audioPlayer;
    }

    /**
     * A method to get whether the {@link AudioPlayer} can be provided with audio.
     *
     * @return true to continue playback, false to pause playback.
     * @since Azote
     */
    @Override
    public boolean canProvide()
    {
        if (lastFrame == null) lastFrame = audioPlayer.provide();
        return lastFrame != null;
    }

    /**
     * If the method {@link #canProvide()} is true, then this method will be used to get a byte of 20ms of audio.
     *
     * @return A byte containing 20ms of audio.
     * @since Azote
     */
    @Override
    public byte[] provide20MsAudio()
    {
        if (lastFrame == null) lastFrame = audioPlayer.provide();
        byte[] data = lastFrame != null ? lastFrame.data : null;
        lastFrame = null;
        return data;
    }

    /**
     * A method to determine if the audio is opus.
     *
     * @return Should always be true.
     * @since Azote
     */
    @Override
    public boolean isOpus()
    {
        return true;
    }
}