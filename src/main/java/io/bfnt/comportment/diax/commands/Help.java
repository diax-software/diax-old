package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.api.command.Command;
import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

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

      //  DiaxCommand::des
        //String commands = String.join(",", getCommands().stream().map(DiaxCommand.getClass.getAnnotation(CommandDescription.class).b));
        StringBuilder sb = new StringBuilder();
        for (DiaxCommand command : getCommands().descendingSet())
        {
            CommandDescription cd = command.getCommandAnnotation();
            sb.append(String.format("`%s %s%s %s `\n\n", cd.emoji(), prefix(), cd.name(), cd.args()));
        }
        return sb + "";
    }
}