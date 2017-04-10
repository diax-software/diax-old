package me.diax.bot.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.diax.bot.lib.audio.DiaxGuildMusicManager;
import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comportment on 10/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"song", "current"}, guildOnly = true)
public class SongCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String args) {
        AudioTrack track = DiaxGuildMusicManager.getManagerFor(trigger.getGuild()).player.getPlayingTrack();
        trigger.getChannel().sendMessage(DiaxUtil.musicEmbed("Currently playing:\n" + (track == null ? "Nothing." : DiaxUtil.getTrackInfo(track)))).queue();
    }
}