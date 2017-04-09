package me.diax.bot.commands.music;

import me.diax.bot.lib.audio.DiaxGuildMusicManager;
import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by NachtRaben on 4/5/2017.
 */

@DiaxCommandDescription(triggers = {"shuffle"}, guildOnly = true, description = "Shuffles the current playlist.")
public class ShuffleCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String truncated) {
        DiaxGuildMusicManager manager = DiaxGuildMusicManager.getManagerFor(trigger.getGuild());
        if (manager.scheduler.shuffle()) {
            trigger.getChannel().sendMessage(DiaxUtil.musicEmbed("The queue has been shuffled.")).queue();
        } else {
            trigger.getChannel().sendMessage(DiaxUtil.errorEmbed("Could **not** shuffle the queue.")).queue();
        }
    }
}