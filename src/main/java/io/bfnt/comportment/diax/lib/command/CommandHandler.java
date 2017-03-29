package io.bfnt.comportment.diax.lib.command;

import io.bfnt.comportment.diax.lib.Diax;
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
        //TODO: Implement command handler.
        String message = event.getMessage().getRawContent();
        if (!message.startsWith(getPrefix())) return;
        log(String.format("%s | %s", makeName(event.getAuthor()), event.getMessage().getRawContent()));
    }
}