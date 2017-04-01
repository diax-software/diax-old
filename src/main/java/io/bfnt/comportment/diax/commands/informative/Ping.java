package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

import java.time.temporal.ChronoUnit;

/**
 * Created by Comporment on 01/04/2017 at 21:30
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(description = "Tells you the pings for Diax.", triggers = {"ping", "pong", "pang", "peng", "pung"})
public class Ping extends DiaxCommand
{
    public void execute(Message trigger)
    {
        trigger.getChannel().sendMessage(makeEmbed().setDescription("Pinging...").build()).queue(message
                -> message.editMessage(makeEmbed().setDescription(String.format("\uD83D\uDCE7 Response: %sms\n❤ Heartbeat: %sms", trigger.getCreationTime().until(message.getCreationTime(), ChronoUnit.MILLIS), trigger.getJDA().getPing())).build()));
    }
}