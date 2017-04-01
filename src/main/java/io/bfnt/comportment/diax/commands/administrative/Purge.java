package io.bfnt.comportment.diax.commands.administrative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 31/03/2017 at 12:29
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"clean", "purge", "clear"}, permission = Permission.MESSAGE_MANAGE, minimumArgs = 1, guildOnly = true)
public class Purge extends DiaxCommand
{
    /**
     * A command which removes 100 messages from the {@link net.dv8tion.jda.core.entities.Guild} the command was used in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger)
    {
        trigger.getChannel().getHistory().retrievePast(100).queue(messages -> trigger.getTextChannel().deleteMessages(messages).queue());
    }
}