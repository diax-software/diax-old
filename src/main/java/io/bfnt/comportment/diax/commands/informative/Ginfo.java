package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;

import java.time.temporal.ChronoUnit;

import static io.bfnt.comportment.diax.util.Utils.makeEmbed;
import static io.bfnt.comportment.diax.util.Utils.makeName;

/**
 * Created by Comporment on 01/04/2017 at 21:08
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"ginfo", "guild", "guildinfo"}, guildOnly = true, description = "Gives you information about the guild.")
public class Ginfo extends DiaxCommand {
    /**
     * A command which displays information about the {@link Guild} that the {@link DiaxCommand} was executed in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        Guild guild = trigger.getGuild();
        trigger.getChannel().sendMessage(makeEmbed().setAuthor(guild.getName(), guild.getIconUrl(), guild.getIconUrl()).setDescription(String.format("\uD83D\uDE47 Owner: %s\n\uD83C\uDF0D Region: %s\n\n\uD83C\uDFA7 Voice Channels: %s\n\uD83D\uDD8A Text Channels: %s\n\uD83D\uDCD3 Total Channels: %s\n\n\uD83D\uDC65 Members: %s\n\uD83D\uDC64 Roles: %s\n\uD83D\uDD5B Created: %s days ago.", makeName(guild.getOwner().getUser()), guild.getRegion().getName(), guild.getVoiceChannels().size(), guild.getTextChannels().size(), (guild.getTextChannels().size() + guild.getVoiceChannels().size()), guild.getMembers().size(), guild.getRoles().size(), guild.getCreationTime().until(trigger.getCreationTime(), ChronoUnit.DAYS))).setThumbnail(guild.getIconUrl()).build()).queue();
    }
}