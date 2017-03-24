package io.bfnt.comportment.diax.api;

import io.bfnt.comportment.diax.api.command.DiaxCommand;
import io.bfnt.comportment.diax.commands.Help;
import io.bfnt.comportment.diax.commands.Kick;
import io.bfnt.comportment.diax.commands.WhoAmI;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.TreeSet;

/**
 * Created by Comporment on 23/03/2017 at 16:41
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class Diax extends ListenerAdapter
{
    protected MessageBuilder makeMessage(String title, String content)
    {
        return new MessageBuilder().append(String.format("__**%s**__\n\n%s", title, content));
    }
    private Message makeError(String content)
    {
        return makeMessage("Error!", content).build();
    }
    @Deprecated
    protected EmbedBuilder makeEmbed(String title, String content)
    {
        return new EmbedBuilder().setTitle(String.format("__**%s**__", title), "").setDescription(content);
    }
    protected String getNiceName(User user)
    {
        return String.format("%s#%s", user.getName(), user.getDiscriminator());
    }
    protected String prefix()
    {
        return "<>";
    }
    protected TreeSet<DiaxCommand> getCommands()
    {
        return new TreeSet<DiaxCommand>()
        {
            {
                add(new WhoAmI());
                add(new Help());
                add(new Kick());
            }
        };
    }
    protected void selfNoPermission(MessageChannel channel)
    {
        channel.sendMessage(makeError("I do not have enough permission to do this.")).queue();
    }
    protected void noPermission(MessageChannel channel)
    {
        channel.sendMessage(makeError("You do not have enough permission to do this.")).queue();
    }
    protected void notInGuild(MessageChannel channel)
    {
        channel.sendMessage(makeError("This command can not be used in a private message.")).queue();
    }
    protected boolean checkPermission(Member member, Permission permission)
    {
        return member.hasPermission(permission);
    }
}