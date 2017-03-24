package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

/**
 * Created by Comporment on 23/03/2017 at 16:47
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "whoami", emoji = "👤", guildOnly = false)
public class WhoAmI extends DiaxCommand
{
    public void execute(Message trigger)
    {
        User user = trigger.getAuthor();
        String bot = "You are ";
        if (!user.isBot())
            bot += "not ";
        trigger.getChannel().sendMessage(makeMessage("About You:", String.format("Your name is %s-senpai\nWe are in %d mutual guild(s).\n%sa bot.", getNiceName(user), user.getMutualGuilds().size(), bot)).build()).queue();
    }
}