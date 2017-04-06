package io.bfnt.comportment.diax.bot.commands;

import io.bfnt.comportment.diax.bot.lib.audio.DiaxGuildMusicManager;
import io.bfnt.comportment.diax.bot.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.bot.lib.command.DiaxCommandDescription;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by NachtRaben on 4/5/2017.
 */

@DiaxCommandDescription(triggers = {"shuffle"})
public class ShuffleCommand extends DiaxCommand {

	@Override
	public void execute(Message trigger, String truncated) {
		DiaxGuildMusicManager manager = DiaxGuildMusicManager.getManagerFor(trigger.getGuild());
		if(manager.scheduler.shuffle())
			trigger.getTextChannel().sendMessage("Queue shuffled.").queue();
	}

}
