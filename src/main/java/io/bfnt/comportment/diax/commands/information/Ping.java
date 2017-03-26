package io.bfnt.comportment.diax.commands.information;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

import java.time.temporal.ChronoUnit;

/**
 * Created by Comporment on 26/03/2017 at 14:40
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "ping", emoji = "⏱")
public class Ping extends DiaxCommand
{
    public void execute(Message trigger)
    {
        trigger.getChannel().sendMessage("⏱ Pinging...").queue(pinging
                -> pinging.editMessage("⏱ Ping: " + trigger.getCreationTime().until(pinging.getCreationTime(), ChronoUnit.MILLIS) + "ms"));
    }
}
