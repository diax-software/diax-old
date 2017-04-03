package io.bfnt.comportment.diax.lib.command;

import io.bfnt.comportment.diax.DiaxProperties;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.bfnt.comportment.diax.util.Utils.*;

/**
 * Created by Comporment on 28/03/2017 at 16:49
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class CommandHandler extends ListenerAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Commands commands;
    private final DiaxProperties properties;

    @Inject
    public CommandHandler(Commands commands, DiaxProperties properties) {
        this.commands = commands;
        this.properties = properties;
    }

    /**
     * Method that is fired when the {@link net.dv8tion.jda.core.JDA} receives a message.
     *
     * @param event MessageReceivedEvent which fires the method.
     * @since Azote
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getRawContent();
        if (event.getAuthor().isBot()) return;
        if (!content.startsWith(properties.getCommandPrefix())) return;
        logger.info(String.format("%s | %s", makeName(event.getAuthor()), content));
        content = content.replaceFirst(properties.getCommandPrefix(), "").trim().toLowerCase();
        String msg = content;

        CommandDescription command = commands.find(msg);
        if (command != null) {
            execute(commands.newInstance(command), event.getMessage(), msg);
        }
    }

    /**
     * Method that attempts to execute a {@link DiaxCommand} if the content if contents have been met.
     *
     * @param command   The {@link DiaxCommand} to execute.
     * @param message   The {@link Message}
     * @param truncated The truncated version of the message content without the prefix or the command trigger.
     */
    private void execute(DiaxCommand command, Message message, String truncated) {
        if (truncated.split(" ").length < 1 + command.getMinimumArgs()) {
            message.getChannel().sendMessage(makeEmbed().addField("Error!", "You did not specify enough args!", false).build()).queue();
            return;
        }
        switch (message.getChannelType()) {
            case TEXT: {
                if (!checkPermission(message.getAuthor(), message.getGuild(), command.getPermission())) {
                    message.getChannel().sendMessage(makeEmbed().addField("Error!", "You do not have enough permission to do that.", false).build()).queue();
                    return;
                }
                break;
            }
            default: {
                if (command.getGuildOnly()) {
                    message.getChannel().sendMessage(makeEmbed().addField("Error!", "This command can not be used in a private message.", false).build()).queue();
                    return;
                }
                break;
            }
        }
        if (command.getOwnerOnly()) {
            if (!message.getAuthor().getId().equals(getOwnerId())) {
                message.getChannel().sendMessage(makeEmbed().addField("Error!", "Like I would give you permission to do this.", false).build()).queue();
                return;
            }
        }
        if (command.getDono)
        try {
            command.execute(message);
        } catch (PermissionException e) {
            message.getChannel().sendMessage(makeEmbed().addField("Error!", "I do not have permission to do this.", false).build()).queue();
        } catch (Exception e) {
            message.getChannel().sendMessage("An error occurred, please contact Comportment#9489 with more info.").queue();
            e.printStackTrace();
        }
    }
}