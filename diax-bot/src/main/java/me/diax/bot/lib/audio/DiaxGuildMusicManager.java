package me.diax.bot.lib.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.core.entities.Guild;

import java.util.HashMap;

/**
 * Created by Comporment on 04/04/2017 at 22:07
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxGuildMusicManager {

    private static HashMap<String, DiaxGuildMusicManager> MANAGERS;
    private static DefaultAudioPlayerManager PLAYER_MANAGER;

    static {
        MANAGERS = new HashMap<>();
        PLAYER_MANAGER = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER);
    }

    public final AudioPlayer player;
    public final DiaxTrackScheduler scheduler;
    public final Guild guild;

    public DiaxGuildMusicManager(Guild guild) {
        player = PLAYER_MANAGER.createPlayer();
        scheduler = new DiaxTrackScheduler(this);
        this.guild = guild;
        player.addListener(scheduler);
        guild.getAudioManager().setSendingHandler(getSendHandler());
    }

    public static DiaxGuildMusicManager getManagerFor(Guild guild) {
        return MANAGERS.computeIfAbsent(guild.getId(), (i) -> new DiaxGuildMusicManager(guild));
    }

    public DefaultAudioPlayerManager getPlayerManager() {
        return PLAYER_MANAGER;
    }

    public DiaxAudioPlayerSendHandler getSendHandler() {
        return new DiaxAudioPlayerSendHandler(player);
    }
}