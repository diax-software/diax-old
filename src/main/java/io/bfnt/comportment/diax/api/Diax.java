package io.bfnt.comportment.diax.api;

import io.bfnt.comportment.diax.api.command.DiaxCommand;
import io.bfnt.comportment.diax.commands.Help;
import io.bfnt.comportment.diax.commands.WhoAmI;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

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
    @Deprecated
    protected EmbedBuilder makeEmbed(String title, String content)
    {
        return new EmbedBuilder().setTitle(String.format("__**%s**__", title), "").setDescription(content);
    }
    protected String getNiceName(User user)
    {
        return String.format("%s#%s", user.getName(), user.getDiscriminator());
    }
    protected String prefix(){

        return "<>";
    }
    protected List<DiaxCommand> getCommands()
    {
        return new ArrayList<DiaxCommand>()
        {
            {
                add(new Help());
                add(new WhoAmI());
            }
        };
    }
}