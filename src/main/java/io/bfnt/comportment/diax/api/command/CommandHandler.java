package io.bfnt.comportment.diax.api.command;

import io.bfnt.comportment.diax.api.Diax;
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
        String command = event.getMessage().getRawContent().replaceFirst(prefix(), "").trim();
        for (DiaxCommand i : getCommands())
        {
            if (i.getName().equals(command.split(" ")[0]))
            {
                i.execute(event.getMessage());
            }
        }
    }
}