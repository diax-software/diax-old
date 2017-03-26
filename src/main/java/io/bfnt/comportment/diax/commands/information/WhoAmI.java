package io.bfnt.comportment.diax.commands.information;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Comporment on 23/03/2017 at 16:47
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "whoami", emoji = "👤")
public class WhoAmI extends DiaxCommand
{
    public void execute(Message trigger)
    {
        User user = trigger.getAuthor();
        String bot = "You are ";
        if (!user.isBot()) bot += "not ";
        List<User> same = new ArrayList<>();
        int count = -1;
        for (Guild guild : trigger.getJDA().getGuilds())
        {
            for (Member member : guild.getMembers())
            {
                if (member.getUser().getDiscriminator().equals(user.getDiscriminator()) && !same.contains(member.getUser()))
                {
                    count += 1;
                    same.add(member.getUser());
                }
            }
        }
        trigger.getChannel().sendMessage(makeMessage("About You:", String.format("Your name is %s-senpai\nWe are in %d mutual guild(s).\n%sa bot. I can see %d other people with the same discriminator as you.", getNiceName(user), user.getMutualGuilds().size(), bot, count)).build()).queue();
    }
}