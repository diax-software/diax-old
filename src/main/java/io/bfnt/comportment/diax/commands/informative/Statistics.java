package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 02/04/2017 at 10:27
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"statistics", "stats"}, description = "Displays some statistics about Diax.")
public class Statistics extends DiaxCommand
{
    @Override
    public void execute(Message trigger)
    {

    }
}