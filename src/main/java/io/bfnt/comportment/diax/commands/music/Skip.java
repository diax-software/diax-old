package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.lib.music.MusicUtil;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 03/04/2017 at 07:37
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"skip", "next"}, description = "Skips the currently playing song in the playlist.", permission = Permission.VOICE_MOVE_OTHERS, guildOnly = true)
public class Skip extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which skips the current playing song in the queue.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        MusicUtil.skip(MusicUtil.getMusicManager(trigger.getTextChannel()));
        trigger.getChannel().sendMessage(makeEmbed().addField("Skipped!", "The current playing song has been skipped!", false).build()).queue();
    }
}