package io.bfnt.comportment.diax.bot.lib.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 * Created by Comporment on 04/04/2017 at 22:07
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxGuildMusicManager {
    public final AudioPlayer player;
    public final DiaxTrackScheduler scheduler;
    private final TextChannel channel;

    public DiaxGuildMusicManager(AudioPlayerManager manager, TextChannel channel) {
        player = manager.createPlayer();
        scheduler = new DiaxTrackScheduler(player, channel);
        player.addListener(scheduler);
        this.channel = channel;
        channel.getGuild().getAudioManager().setSendingHandler(getSendHandler());
    }

    public DiaxAudioPlayerSendHandler getSendHandler() {
        return new DiaxAudioPlayerSendHandler(player);
    }

    public TextChannel getChannel() {
        return channel;
    }
}