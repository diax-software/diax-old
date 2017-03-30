package io.bfnt.comportment.diax.lib.command;

import io.bfnt.comportment.diax.lib.Diax;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Comporment on 28/03/2017 at 16:49
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class CommandHandler extends Diax
{
    /**
     * Method that is fired when the {@link net.dv8tion.jda.core.JDA} receives a message.
     *
     * @param event MessageReceivedEvent which fires the method.
     * @since Azote
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message message = event.getMessage();
        String content = message.getRawContent();
        if (!content.startsWith(getPrefix())) return;
        log(String.format("%s | %s", makeName(event.getAuthor()), content));
        content = content.replaceFirst(getPrefix(), "").trim().toLowerCase();
        for (DiaxCommand command : getCommands())
        {
            if (content.split(" ").length < command.getMinimumArgs())
            {
                message.getChannel().sendMessage("Nope").queue();
            }
            if (command.getTrigger().equals(content.split(" ", 1)[0]))
            {

                if (!event.getChannelType().equals(ChannelType.TEXT))
                {
                    if (command.getGuildOnly())
                    {
                        message.getChannel().sendMessage("Nope").queue();
                    }
                    else
                    {
                        message.getChannel().sendMessage("yep").queue();
                    }
                }
                else
                {
                    if (!checkPermission(message.getAuthor(), message.getGuild(), command.getPermission()))
                    {
                        message.getChannel().sendMessage("nope").queue();
                    }
                    else
                    {
                        message.getChannel().sendMessage("yep").queue();
                    }
                }
            }
        }
    }
}