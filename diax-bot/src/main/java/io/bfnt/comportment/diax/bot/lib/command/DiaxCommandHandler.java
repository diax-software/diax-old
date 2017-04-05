package io.bfnt.comportment.diax.bot.lib.command;

import io.bfnt.comportment.diax.bot.DiaxProperties;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by Comporment on 04/04/2017 at 22:32
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxCommandHandler extends ListenerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DiaxCommands commands;
    private final DiaxProperties properties;

    @Inject
    public DiaxCommandHandler(DiaxCommands commands, DiaxProperties properties) {
        this.commands = commands;
        this.properties = properties;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getRawContent();
        if (event.getAuthor().isBot()) return;
        if (!content.startsWith(properties.getPrefix())) return;
        System.out.println(String.format("%s | %s", DiaxCommandUtil.makeName(event.getAuthor()), content));
        String truncated = content.replaceFirst(properties.getPrefix(), "").trim().toLowerCase();
        DiaxCommandDescription command = commands.find(truncated.split(" ")[0]);
        if (command != null) {
            execute(commands.newInstance(command), event.getMessage(), truncated);
        }
    }

    private void execute(DiaxCommand command, Message message, String truncated) {
        if (truncated.split(" ").length < 1 + command.getMinimumArgs()) {
            message.getChannel().sendMessage(DiaxCommandUtil.error("You did not specify enouggh args!")).queue();
            return;
        }
        switch (message.getChannelType()) {
            case TEXT: {
                if (!DiaxCommandUtil.checkPermission(message.getAuthor(), message.getGuild(), command.getPermission())) {
                    message.getChannel().sendMessage(DiaxCommandUtil.error("You do not have enough permission to do that!")).queue();
                    return;
                }
                break;
            }
            default: {
                if (command.getGuildOnly()) {
                    message.getChannel().sendMessage(DiaxCommandUtil.error("This command cannot be used in a private message.")).queue();
                    return;
                }
                break;
            }
        }
        if (command.getOwnerOnly()) {
            if (!message.getAuthor().getId().equals(DiaxCommandUtil.getOwnerID())) {
                message.getChannel().sendMessage(DiaxCommandUtil.error("This is an owner only command, baka.")).queue();
                return;
            }
        }
        try {
            command.execute(message, truncated);
        } catch (PermissionException e) {
            message.getChannel().sendMessage(DiaxCommandUtil.error("I do not have enough permission to do that.")).queue();
        } catch (Exception e) {
            message.getChannel().sendMessage("An error occurred, please contact Comportment#9489 with more info.").queue();
            e.printStackTrace();
        }
    }
}
