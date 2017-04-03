package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.lib.music.MusicUtil;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 03/04/2017 at 07:15
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"shuffle", "random"}, guildOnly = true, permission = Permission.VOICE_MOVE_OTHERS, description = "Shuffles and randomises the current playing playlist.")
public class Shuffle extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which shuffles the currently playing queue in the {@link net.dv8tion.jda.core.entities.Guild} the command was sent in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        MusicUtil.shuffle(MusicUtil.getMusicManager(trigger.getTextChannel()));
        trigger.getChannel().sendMessage(makeEmbed().addField("Shuffled!", "The queue has been shuffled!", false).build()).queue();
    }
}