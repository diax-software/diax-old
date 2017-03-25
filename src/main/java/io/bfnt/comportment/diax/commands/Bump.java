package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.api.BumpTimer;
import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 25/03/2017 at 17:46
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "bump", guildOnly = true, emoji = "🎉")
public class Bump extends DiaxCommand
{
    private int cooldown = 10;
    public void execute(Message trigger)
    {
        if (BumpTimer.getBumps().containsKey(trigger.getGuild()))
        {
            long timeLeft = (trigger.getCreationTime().toEpochSecond() - BumpTimer.getBumps().get(trigger.getGuild()));
            if (timeLeft >= cooldown)
            {
                bump(trigger);
            }
            else
            {
                trigger.getChannel().sendMessage(makeMessage(" ", "Please wait " + timeLeft + " more seconds more to do this.").build()).queue();
            }
        }
        else
        {
            bump(trigger);
        }
    }
    private void bump(Message trigger)
    {
        BumpTimer.removeBump(trigger.getGuild());
        trigger.getChannel().sendMessage(makeMessage("Bumped!", trigger.getGuild().getName() + " has been bumped!").build()).queue();
        BumpTimer.addBump(trigger.getGuild(), trigger.getCreationTime().toEpochSecond());
    }
}