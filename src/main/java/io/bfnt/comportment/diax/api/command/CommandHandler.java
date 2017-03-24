package io.bfnt.comportment.diax.api.command;

import io.bfnt.comportment.diax.api.Diax;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

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
                if (cd.minimumArgs() + 1 > command.split(" ").length)
                {
                    notEnoughArgs(channel);
                }
                else if (cd.guildOnly() && channel.getType().equals(ChannelType.PRIVATE))
                {
                    notInGuild(channel);
                }
                else if (checkPermission(event.getGuild().getMember(event.getAuthor()), cd.permission()))
                {
                    if (checkPermission(event.getGuild().getMember(event.getJDA().getSelfUser()), cd.permission()))
                    {
                        i.execute(event.getMessage());
                    }
                    else
                    {
                        makeError(channel, ErrorType.NO_PERMISSION);
                    }
                }
                else
                {
                    noPermission(channel);
                }
                return;
            }
        }
    }
}