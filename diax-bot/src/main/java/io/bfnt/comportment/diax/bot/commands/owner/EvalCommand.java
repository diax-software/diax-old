package io.bfnt.comportment.diax.bot.commands.owner;

import io.bfnt.comportment.diax.bot.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.bot.lib.command.DiaxCommandDescription;
import net.dv8tion.jda.core.entities.Message;

import javax.script.ScriptEngine;

/**
 * Created by Comportment on 07/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"eval", "hack", "evaluate"}, ownerOnly = true)
public class EvalCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String truncated) {
        ScriptEngine engine;
    }
}