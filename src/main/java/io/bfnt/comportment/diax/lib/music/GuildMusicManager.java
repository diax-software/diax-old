package io.bfnt.comportment.diax.lib.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 * Created by Comporment on 02/04/2017 at 16:18
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class GuildMusicManager {
    public final AudioPlayer player;
    public final TrackScheduler scheduler;
    private final TextChannel channel;

    /**
     * Constructor which wraps a {@link AudioPlayerManager} to make a {@link GuildMusicManager}
     *
     * @param manager The {@link GuildMusicManager} which was created.
     * @param channel {@link TextChannel} to get the {@link Guild} associated with the {@link GuildMusicManager}
     * @since Azote
     */
    public GuildMusicManager(AudioPlayerManager manager, TextChannel channel) {
        player = manager.createPlayer();
        scheduler = new TrackScheduler(player, channel);
        player.addListener(scheduler);
        this.channel = channel;
        channel.getGuild().getAudioManager().setSendingHandler(getSendHandler());
    }

    /**
     * Method which wraps the {@link AudioPlayer} to create a {@link AudioPlayerSendHandler}
     *
     * @return The {@link AudioPlayerSendHandler} which was created.
     * @since Azote
     */
    public AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(player);
    }

    /**
     * A method to get the {@link TextChannel} associated with the {@link #GuildMusicManager(AudioPlayerManager, TextChannel)}
     *
     * @return The {@link TextChannel} associated with the {@link #GuildMusicManager(AudioPlayerManager, TextChannel)}
     * @since Azote
     */
    public TextChannel getChannel() {
        return channel;
    }
}