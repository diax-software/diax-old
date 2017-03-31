package io.bfnt.comportment.diax.commands.administrative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 31/03/2017 at 12:29
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"clean", "purge", "clear"}, permission = Permission.MESSAGE_MANAGE, minimumArgs = 1, guildOnly = true)
public class Purge
{
    public void exeucte(Message trigger)
    {

        trigger.getChannel().getHistory().retrievePast(100).queue(messages -> trigger.getTextChannel().deleteMessages(messages).queue());
    }
}