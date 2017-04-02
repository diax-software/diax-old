package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 02/04/2017 at 16:21
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"play", "queue"}, permission = Permission.VOICE_CONNECT, guildOnly = true, minimumArgs = 1, description = "Queues the given track url.")
public class Play extends DiaxCommand
{
    @Override
    public void execute(Message trigger)
    {
        //Do stuff
    }
}