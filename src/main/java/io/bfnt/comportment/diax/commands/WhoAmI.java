package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 23/03/2017 at 16:47
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class WhoAmI extends DiaxCommand
{
    @CommandDescription(name = "whoami", description = "Tells you about yourself.", emoji = "👤")
    public void execute(Message trigger)
    {
        trigger.getChannel().sendMessage(makeMessage("About You:", String.format("Your name is: %s", trigger.getAuthor().getName())).build()).queue();
    }
}