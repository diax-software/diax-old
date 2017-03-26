package io.bfnt.comportment.diax.api.command;

import io.bfnt.comportment.diax.api.Diax;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
/**
 * Created by Comporment on 23/03/2017 at 20:14
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */

public final class CommandHandler extends Diax
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (!event.getMessage().getRawContent().startsWith(prefix())) return;
        String command = event.getMessage().getRawContent().replaceFirst(prefix(), "").trim().toLowerCase();
        MessageChannel channel = event.getChannel();
        for (DiaxCommand i : getCommands())
        {
            CommandDescription cd = i.getCommandAnnotation();
            if (cd.name().equals(command.split(" ")[0]))
            {
                if (cd.minimumArgs() + 1 > command.split(" ").length)
                {
                    makeError(channel, ErrorType.NOT_ENOUGH_ARGS);
                }
                else if (!cd.guildOnly() && channel.getType().equals(ChannelType.PRIVATE))
                {
                    i.execute(event.getMessage());
                    return;
                }
                else if (cd.guildOnly() && (channel.getType().equals(ChannelType.PRIVATE)))
                {
                    makeError(channel, ErrorType.NOT_IN_GUILD);
                }
                else if (checkPermission(event.getGuild().getMember(event.getAuthor()), cd.permission()))
                {
                    try
                    {
                        i.execute(event.getMessage());
                    }
                    catch (PermissionException e)
                    {
                        makeError(channel, ErrorType.SELF_NO_PERMISSION);
                    }
                    catch (Exception e)
                    {
                        makeError(channel, ErrorType.UNKNOWN);
                        e.printStackTrace();
                    }
                }
                else
                {
                    makeError(channel, ErrorType.NO_PERMISSION);
                }
                return;
            }
        }
    }
}