package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 28/03/2017 at 19:07
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"help", "?", "helpme"}, description = "Gives you help for Diax.")
public class Help extends DiaxCommand
{
    /**
     * The help command which displays all of the other commands.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger)
    {

    }
}