package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.Main;
import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 23/03/2017 at 17:49
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Help extends DiaxCommand
{
    @CommandDescription(name = "help", description = "Gives you help about the commands.", emoji = "❔", args = "[command name]")
    public void execute(Message message)
    {
        message.getChannel().sendMessage(makeMessage("Commands", makeCommands()).build()).queue();
    }
    private String makeCommands()
    {
        StringBuilder sb = new StringBuilder();
        for (DiaxCommand command : new Main().getCommands())
        {
            CommandDescription annotation = command.getClass().getAnnotation(CommandDescription.class);
            sb.append(String.format("%s %s `%s`\n", annotation.emoji(), prefix(), annotation.args()));
        }
        return sb + "";
    }
}