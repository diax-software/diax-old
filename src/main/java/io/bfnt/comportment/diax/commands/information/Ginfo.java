package io.bfnt.comportment.diax.commands.information;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 25/03/2017 at 16:38
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(guildOnly = true, name = "ginfo", emoji = "👥")
public class Ginfo extends DiaxCommand
{
    public void execute(Message trigger)
    {
        Guild guild = trigger.getTextChannel().getGuild();
        trigger.getChannel().sendMessage(makeMessage("About: " + guild.getName(), "").build()).queue();
    }
}