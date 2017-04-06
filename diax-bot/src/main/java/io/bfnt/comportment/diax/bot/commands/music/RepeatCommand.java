package io.bfnt.comportment.diax.bot.commands.music;

import io.bfnt.comportment.diax.bot.lib.audio.DiaxGuildMusicManager;
import io.bfnt.comportment.diax.bot.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.bot.lib.command.DiaxCommandDescription;
import io.bfnt.comportment.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by NachtRaben on 4/5/2017.
 */

@DiaxCommandDescription(triggers = {"repeat"}, guildOnly = true)
public class RepeatCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String truncated) {
        DiaxGuildMusicManager manager = DiaxGuildMusicManager.getManagerFor(trigger.getGuild());
        boolean newValue = !manager.scheduler.isRepeating();
        String message = "The track is %s repeating.";
        if (newValue) {
            message = String.format(message, "now");
        } else {
            message = String.format(message, "no longer");
        }
        manager.scheduler.setRepeating(newValue);
        trigger.getChannel().sendMessage(DiaxUtil.musicEmbed(message)).queue();
    }
}