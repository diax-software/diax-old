package me.diax.bot.commands.statistics;

import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;

import java.time.temporal.ChronoUnit;

/**
 * Created by Comportment on 11/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"ginfo", "guildinfo", "guild"}, guildOnly = true)
public class Ginfo extends DiaxCommand {

    @Override
    public void execute(Message trigger, String args) {
        Guild guild = trigger.getGuild();
        trigger.getChannel().sendMessage(
                DiaxUtil.defaultEmbed()
                        .setAuthor(guild.getName(), guild.getIconUrl(), guild.getIconUrl())
                        .setDescription(String.format("\uD83D\uDE47 Owner: %s\n\uD83C\uDF0D Region: %s\n\n\uD83C\uDFA7 Voice Channels: %s\n\uD83D\uDD8A Text Channels: %s\n\uD83D\uDCD3 Total Channels: %s\n\n\uD83E\uDD16 Bots: %s\n\uD83D\uDC65 Users: %s\n➕ Total: %s\n\n♦ Shard: %s\n\uD83D\uDC64Roles: %s\n\uD83D\uDD5B Created: %s days ago.",
                                DiaxUtil.makeName(guild.getOwner().getUser()),
                                guild.getRegion().getName(),
                                guild.getVoiceChannels().size(),
                                guild.getTextChannels().size(),
                                (guild.getTextChannels().size() + guild.getVoiceChannels().size()),
                                guild.getMembers().stream().filter(member -> member.getUser().isBot()).count(),
                                guild.getMembers().stream().filter(member -> ! member.getUser().isBot()).count(),
                                guild.getMembers().size(),
                                DiaxUtil.getShard(guild),
                                guild.getRoles().size(),
                                guild.getCreationTime().until(trigger.getCreationTime(), ChronoUnit.DAYS)))
                        .setThumbnail(guild.getIconUrl()).build()).queue();
    }
}
