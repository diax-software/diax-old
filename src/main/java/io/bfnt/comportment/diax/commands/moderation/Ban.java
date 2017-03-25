package io.bfnt.comportment.diax.commands.moderation;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 24/03/2017 at 21:19
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "ban", minimumArgs = 1, permission = Permission.BAN_MEMBERS, args = "@mention")
public class Ban extends DiaxCommand
{
    public void execute(Message trigger)
    {

    }
}
