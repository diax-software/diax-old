package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.Main;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 23/03/2017 at 17:49
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Help extends DiaxCommand
{
    public void execute(Message message)
    {
        message.getChannel().sendMessage(makeMessage("Commands", makeCommands()).build()).queue();
    }
    public String getEmoji()
    {
        return "❔";
    }
    public String getName()
    {
        return "help";
    }
    public String getArgs()
    {
        return "[command name]";
    }
    public String getDescription()
    {
        return "Gives you help for Diax and his commands.";
    }
    private String makeCommands()
    {
        StringBuilder sb = new StringBuilder();
        for (DiaxCommand command : getCommands())
        {
            sb.append(String.format("%s %s%s `%s `\n\n", command.getEmoji(), prefix(), command.getName(), command.getArgs()));
        }
        return sb + "";
    }
}