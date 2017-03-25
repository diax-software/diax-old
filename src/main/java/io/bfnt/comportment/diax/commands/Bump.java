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
    public void execute(Message trigger)
    {
        if (BumpTimer.getBumps().containsKey(trigger.getGuild()))
        {
            long timeleft = (trigger.getCreationTime().toEpochSecond() - BumpTimer.getBumps().get(trigger.getGuild()));
            if (timeleft >= 5) BumpTimer.removeBump(trigger.getGuild());
            trigger.getChannel().sendMessage("" + timeleft).queue();
        }
        else
        {
            trigger.getChannel().sendMessage("not in the timer").queue();
            BumpTimer.addBump(trigger.getGuild(), trigger.getCreationTime().toEpochSecond());
        }
    }
}