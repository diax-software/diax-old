package io.bfnt.comportment.diax.commands.misc;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 26/03/2017 at 18:48
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "echo")
public class Echo extends DiaxCommand
{
    public void execute(Message trigger)
    {
        trigger.getChannel().sendMessage(trigger.getRawContent().substring(trigger.getRawContent().split(" ")[0].length())).queue();
    }
}
