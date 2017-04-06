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
        manager.scheduler.setRepeating(!manager.scheduler.isRepeating());
        trigger.getChannel().sendMessage(DiaxUtil.musicEmbed(String.format("The track is %s repeating.", manager.scheduler.isRepeating() ? "now" : "no longer"))).queue();
    }
}