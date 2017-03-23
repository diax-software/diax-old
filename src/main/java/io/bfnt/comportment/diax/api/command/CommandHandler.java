package io.bfnt.comportment.diax.api.command;

import io.bfnt.comportment.diax.api.Diax;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.utils.PermissionUtil;

/**
 * Created by Comporment on 23/03/2017 at 20:14
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */

public class CommandHandler extends Diax
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (!event.getMessage().getRawContent().startsWith(prefix())) return;
        String command = event.getMessage().getRawContent().replaceFirst(prefix(), "").trim().toLowerCase();
        MessageChannel channel = event.getChannel();
        for (DiaxCommand i : getCommands())
        {
            CommandDescription cd = i.getClass().getAnnotation(CommandDescription.class);
            if (cd.name().equals(command.split(" ")[0]))
            {
                switch (event.getChannelType())
                {
                    case TEXT:
                    {
                        try
                        {
                            if (checkPermission(event.getGuild().getMember(event.getAuthor()), cd.permission())) i.execute(event.getMessage());
                        }
                        catch (PermissionException e1)
                        {
                            selfNoPermission(channel);
                        }
                        break;
                    }
                    default:
                    {
                        notInGuild(channel);
                        break;
                    }
                }
                break;
            }
        }
    }
}