package me.diax.bot.lib.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.exceptions.PermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BlockingQueue<DiaxAudioTrack> queue;
    private DiaxGuildMusicManager manager;
    private boolean repeating = false;

    private DiaxAudioTrack currentTrack;
    private DiaxAudioTrack lastTrack;

    public DiaxTrackScheduler(DiaxGuildMusicManager manager) {
        this.manager = manager;
        this.queue = new LinkedBlockingQueue<>();
    }

    public BlockingQueue<DiaxAudioTrack> getQueue() {
        return queue;
    }

    public boolean play(DiaxAudioTrack track) {
        if (track != null) {
            currentTrack = track;
            return manager.player.startTrack(track.getTrack(), false);
        }
        return false;
    }

    public void queue(DiaxAudioTrack track) {
        if (queue.offer(track) && this.currentTrack == null) {
            skip();
        }
    }

    public boolean shuffle() {
        if (!queue.isEmpty()) {
            List<DiaxAudioTrack> tracks = new ArrayList<>();
            queue.drainTo(tracks);
            Collections.shuffle(tracks);
            queue.addAll(tracks);
            return true;
        }
        return false;
    }

    public boolean skip() {
        lastTrack = currentTrack;
        if (repeating) {
            if (currentTrack != null)
                play(currentTrack.clone());
            else if (!queue.isEmpty())
                play(this.queue.poll());
        } else if (queue.isEmpty()) {
            if (currentTrack != null) {
                currentTrack.getChannel().sendMessage(DiaxUtil.musicEmbed("Queue concluded.")).queue();
            }
            stop();
        } else {
            play(queue.poll());
        }
        return true;
    }

    public void stop() {
        logger.debug("Stopping the track.");
        queue.clear();
        manager.player.stopTrack();
        currentTrack = null;
        manager.guild.getAudioManager().closeAudioConnection();
    }

    public boolean isRepeating() {
        return this.repeating;
    }

    public void setRepeating(boolean repeating) {
        logger.warn("Current track: " + (currentTrack == null ? "null" : currentTrack.hashCode()));
        this.repeating = repeating;
    }

    public VoiceChannel getVoiceChannel(Guild guild, Member member) {
        VoiceChannel vc = null;
        if (member != null && member.getVoiceState().inVoiceChannel()) {
            vc = member.getVoiceState().getChannel();
        } else if (!guild.getVoiceChannels().isEmpty()) {
            vc = guild.getVoiceChannels().get(0);
        }
        return vc;
    }

    public boolean joinVoiceChannel() {
        Guild guild = this.manager.guild;
        Member member = guild.getMember(currentTrack.getRequester());
        VoiceChannel voiceChannel = getVoiceChannel(guild, member);
        if (!guild.getAudioManager().isConnected() || this.queue.isEmpty()) {
            try {
                guild.getAudioManager().openAudioConnection(voiceChannel);
            } catch (PermissionException exception) {
                return false;
            }
        }
        return true;
    }

    public DiaxAudioTrack getCurrentTrack() {
        return currentTrack;
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        logger.debug("Starting the player.");
        if (joinVoiceChannel()) {
            if (!repeating) {
                AudioTrackInfo info = this.currentTrack.getTrack().getInfo();
                User requester = currentTrack.getRequester();
                currentTrack.getChannel().sendMessage(DiaxUtil.musicEmbed(String.format("Now playing: `%s ` by `%s `\nRequested by: `%s `", info.title, info.author, DiaxUtil.makeName(requester)))).queue();
            } else {
                currentTrack.getChannel().sendMessage(DiaxUtil.musicEmbed("Repeating the last song.")).queue();
            }
        } else {
            currentTrack.getChannel().sendMessage(DiaxUtil.errorEmbed("Could not join that voice channel!")).queue();
            stop();
        }
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason reason) {
        if (reason.mayStartNext) {
            logger.warn("Was told we could play the next track.");
            skip();
        }
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        currentTrack.getChannel().sendMessage(DiaxUtil.errorEmbed("Failed to play the track due to: `" + exception.getMessage() + " `")).queue();
        logger.warn(exception.getMessage(), exception);
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
        currentTrack.getChannel().sendMessage(DiaxUtil.errorEmbed("Got stuck attempting to play track, skipping.")).queue();
        skip();
    }
}