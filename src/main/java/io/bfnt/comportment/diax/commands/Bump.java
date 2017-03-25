package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.HashMap;

/**
 * Created by Comporment on 25/03/2017 at 17:46
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Bump extends DiaxCommand
{
    private HashMap<Guild, Long> guilds = new HashMap<>();
    public void execute(Message trigger)
    {
        Guild guild = trigger.getGuild();
        Long time = trigger.getCreationTime().toEpochSecond();
        if (guilds.containsKey(guild))
        {
            if (guilds.get(trigger.getGuild()) - trigger.getCreationTime().toEpochSecond() > 100)
            {
                guilds.remove(trigger.getGuild());
                trigger.getChannel().sendMessage(makeMessage("Bumped!", "This server has been bumped.").build()).queue();
                guilds.put(guild, time);
            }
            else
            {
                trigger.getChannel().sendMessage(makeMessage("Error!", String.format("Please wait %d more seconds to bump this server again.", guilds.get(guild))).build()).queue();
            }
        }
        else
        {
            guilds.put(guild, time);
            trigger.getChannel().sendMessage(makeMessage("Bumped!", "This server has been bumped!").build()).queue();
        }
    }
    public void bump(TextChannel channel, Long time)
    {
        Guild guild = channel.getGuild();
        if (guilds.containsKey(guild)) guilds.remove(guild);
        channel.sendMessage(makeMessage("Bumped!", "This server has been bumped!").build()).queue(_void ->
                guilds.put(guild, time));
    }
}
