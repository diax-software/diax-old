package me.diax.bot.commands.profile;

import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.command.DiaxCommands;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comportment on 11/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"balance", "bal", "currency"})
public class Balance extends DiaxCommands {

    @Override
    public void execute(Message trigger, String args) {
        trigger.getChannel().sendMessage("SoonTM").queue();
    }
}
