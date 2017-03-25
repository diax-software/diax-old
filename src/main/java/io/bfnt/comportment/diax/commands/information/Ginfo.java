package io.bfnt.comportment.diax.commands.information;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;

import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

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
        trigger.getChannel().sendMessage(makeMessage(guild.getName(), String.format("🙇 Owner: %s\n🌍 Region: %s\n🎧 Voice Channels: %d\n🖊 Text Channels: %d\n📓 Total Channels: %d\n👥 Members: %d\n👤Roles: (%d)\n%s\n🕛 Created: %s day(s) ago.", guild.getOwner().getUser().getName() + "#" + guild.getOwner().getUser().getDiscriminator(), guild.getRegion().getName(), guild.getVoiceChannels().size(), guild.getTextChannels().size(), guild.getVoiceChannels().size() + guild.getTextChannels().size(), guild.getMembers().size(), guild.getRoles().size(), guild.getRoles().stream().map(Role::getName).collect(Collectors.joining(", ")).replaceFirst(", @everyone", "."), guild.getCreationTime().until(trigger.getCreationTime(), ChronoUnit.DAYS))).build()).queue();
    }
}