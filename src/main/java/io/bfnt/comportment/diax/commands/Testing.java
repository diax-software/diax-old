package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 01/04/2017 at 17:48
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"testing"}, ownerOnly = true)
public class Testing extends DiaxCommand
{
    public void execute(Message trigger)
    {
        trigger.getChannel().sendMessage("memes").queue();
    }
}