package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

import java.util.stream.Collectors;

/**
 * Created by Comporment on 23/03/2017 at 17:49
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "help", args = "[command name]", emoji = "❗")
public class Help extends DiaxCommand
{
    public void execute(Message message)
    {
        message.getChannel().sendMessage(makeMessage("Commands", makeCommands()).build()).queue();
    }
    private String makeCommands()
    {
        return getCommands().descendingSet().stream().map(DiaxCommand::getHelpDescription).collect(Collectors.joining("\n\n"));
    }
}