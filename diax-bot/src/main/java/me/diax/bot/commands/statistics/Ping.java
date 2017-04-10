package me.diax.bot.commands.statistics;

import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Message;

import java.time.temporal.ChronoUnit;

/**
 * Created by Comportment on 11/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"ping", "pang", "pung", "peng", "pang"})
public class Ping extends DiaxCommand {

    @Override
    public void execute(Message trigger, String truncated) {
        trigger.getChannel().sendMessage(DiaxUtil.defaultEmbed()
                .setDescription("⏱ Pinging...").build())
                .queue(message -> message.editMessage(DiaxUtil.defaultEmbed().setDescription(
                        String.format("⏱ Response: %dms\n❤ Heartbeat: %dms",
                                trigger.getCreationTime().until(message.getCreationTime(), ChronoUnit.MILLIS),
                                trigger.getJDA().getPing())).build()).queue());
    }
}