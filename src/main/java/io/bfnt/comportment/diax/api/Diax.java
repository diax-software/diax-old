package io.bfnt.comportment.diax.api;

import io.bfnt.comportment.diax.api.command.DiaxCommand;
import io.bfnt.comportment.diax.api.command.ErrorType;
import io.bfnt.comportment.diax.commands.Help;
import io.bfnt.comportment.diax.commands.moderation.Ban;
import io.bfnt.comportment.diax.commands.moderation.Kick;
import io.bfnt.comportment.diax.commands.WhoAmI;
import io.bfnt.comportment.diax.commands.moderation.Softban;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.utils.PermissionUtil;

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
    protected void makeError(MessageChannel channel, ErrorType type)
    {
        channel.sendMessage(makeMessage("Error!", type.getDescription()).build()).queue();
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
                add(new Softban());
                add(new Ban());
            }
        };
    }
    protected boolean checkPermission(Member member, Permission permission)
    {
        return PermissionUtil.checkPermission(member.getGuild(), member, permission);
    }
}