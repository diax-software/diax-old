package io.bfnt.comportment.diax.commands.owner;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by Comporment on 01/04/2017 at 17:48
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"eval"}, ownerOnly = true)
public class Eval extends DiaxCommand
{
    /**
     * A command which evaluates the arguments of the {@link Message} that triggered the command.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger)
    {
        try
        {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.put("jda", trigger.getJDA());
            engine.put("message", trigger);
            engine.put("event", trigger);
            engine.put("guild", trigger.getGuild());
            engine.put("channel", trigger.getChannel());
            trigger.getChannel().sendMessage("```" + engine.eval(trigger.getRawContent().replaceFirst(trigger.getRawContent().split(" ")[0], "")) + "```").queue();
        }
        catch (Exception ignored) {}
    }
}