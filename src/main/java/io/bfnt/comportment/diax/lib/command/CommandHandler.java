package io.bfnt.comportment.diax.lib.command;

import io.bfnt.comportment.diax.lib.Diax;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

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
        String msg = content;
        getCommands().forEach(command ->
        {
            for (String s : command.getTriggers())
                if (msg.startsWith(s))
            {
                execute(command, event.getMessage(), msg);
                return;
            }
        });
    }

    /**
     * Method that attempts to execute a {@link DiaxCommand} if the content if contents have been met.
     *
     * @param command   The {@link DiaxCommand} to execute.
     * @param message   The {@link Message}
     * @param truncated The truncated version of the message content without the prefix or the command trigger.
     */
    private void execute(DiaxCommand command, Message message, String truncated) {
        if (truncated.split(" ").length < command.getMinimumArgs()) {
            message.getChannel().sendMessage(makeEmbed().addField("Error!", "You did not specify enough args!", false).build()).queue();
            return;
        }
        switch (message.getChannelType())
        {
            case TEXT:
            {
                if (!checkPermission(message.getAuthor(), message.getGuild(), command.getPermission()))
                {
                    message.getChannel().sendMessage(makeEmbed().addField("Error!", "You do not have enough permission to do that.", false).build()).queue();
                    return;
                }
                break;
            }
            default:
            {
                if (command.getGuildOnly())
                {
                    message.getChannel().sendMessage(makeEmbed().addField("Error!", "This command can not be used in a private message.", false).build()).queue();
                    return;
                }
                break;
            }
        }
        if (command.getOwnerOnly())
        {
            if (!message.getAuthor().getId().equals(getOwnerId()))
            {
                message.getChannel().sendMessage(makeEmbed().addField("Error!", "Like I would give you permission to do this.", false).build()).queue();
                return;
            }
        }
        try
        {
            command.execute(message);
        }
        catch (PermissionException e)
        {
            message.getChannel().sendMessage(makeEmbed().addField("Error!", "I do not have permission to do this.", false).build()).queue();
        }
        catch (Exception e)
        {
            message.getChannel().sendMessage("An error occurred, please contact Comportment#9489 with more info.").queue();
            e.printStackTrace();
        }
    }
}