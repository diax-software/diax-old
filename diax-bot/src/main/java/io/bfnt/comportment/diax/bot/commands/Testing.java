package io.bfnt.comportment.diax.bot.commands;

import io.bfnt.comportment.diax.bot.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.bot.lib.command.DiaxCommandDescription;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comportment on 05/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"testing"}, minimumArgs = 2, ownerOnly = false, donorOnly = false, guildOnly = true, description = "Something about the command", permission = Permission.ADMINISTRATOR)
public class Testing extends DiaxCommand {

    /**
     * The message which triggered the command, the command args minus the prefix
     */
    public void execute(Message trigger, String truncated) {
        trigger.getChannel().sendMessage(truncated).queue();
    }
}