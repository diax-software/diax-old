package io.bfnt.comportment.diax.commands.moderation;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 25/03/2017 at 11:41
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "p̶u̶r̶g̶e", args = "@mention", guildOnly = true, emoji = "🚓")
public class Purge extends DiaxCommand
{
    public void execute(Message trigger)
    {
       // punish(trigger.getGuild().getMember(trigger.getJDA().getSelfUser()), trigger.getTextChannel(), Punishment.PURGE);
    }
}
