package io.bfnt.comportment.diax.commands.moderation;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 25/03/2017 at 11:41
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "purge", args = "@mention", guildOnly = true, emoji = "🚓")
public class Purge extends DiaxCommand
{
    public void execute(Message trigger)
    {
        int amount;
        try
        {
                amount = Integer.parseInt(trigger.getRawContent().split(" ")[1]);
        }
        catch (NumberFormatException e)
        {
            amount = 2;
        }
        if (amount > 100) amount = 100;
        if (amount < 2) amount = 2;
        trigger.getTextChannel().getHistory().retrievePast(amount).queue(history ->
                trigger.getTextChannel().deleteMessages(history).queue(_void ->
                    trigger.getTextChannel().sendMessage(makeMessage("Deleted!", history.size() + " messages have been deleted.").build()).queue()));
    }
}
