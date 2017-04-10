package me.diax.bot.commands.music;

import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comportment on 10/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"join", "connect"}, permission = Permission.VOICE_MOVE_OTHERS, guildOnly = true)
public class JoinCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String args) {

    }
}
