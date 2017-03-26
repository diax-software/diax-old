package io.bfnt.comportment.diax.commands.information;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 26/03/2017 at 19:22
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "statistics", emoji = "📈")
public class Statistics extends DiaxCommand
{
    public void execute(Message trigger)
    {
        JDA jda = trigger.getJDA();
        trigger.getChannel().sendMessage("Guilds: " + jda.getGuilds().size() + " (2 Premium)\nUsers: " + jda.getUsers().size()).queue();
    }
}