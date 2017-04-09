package me.diax.bot.commands.music;

import me.diax.bot.lib.audio.DiaxGuildMusicManager;
import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by NachtRaben on 4/5/2017.
 */

@DiaxCommandDescription(triggers = {"stop"}, guildOnly = true, description = "Stops playback of music.", permission = Permission.VOICE_MOVE_OTHERS)
public class StopCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String truncated) {
        DiaxGuildMusicManager manager = DiaxGuildMusicManager.getManagerFor(trigger.getGuild());
        manager.scheduler.stop();
        trigger.getChannel().sendMessage(DiaxUtil.musicEmbed("Playback has been stopped.")).queue();
    }
}