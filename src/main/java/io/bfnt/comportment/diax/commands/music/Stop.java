package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.lib.music.MusicUtil;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 03/04/2017 at 18:41
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"stop", "end", "leave", "quit"}, guildOnly = true, permission = Permission.VOICE_MOVE_OTHERS, description = "Stops music from playing and clears the queue.")
public class Stop extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which stops the currently playing {@link io.bfnt.comportment.diax.lib.music.GuildMusicManager} in the {@link net.dv8tion.jda.core.entities.Guild} the {@link Message} was executed in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger) {
        MusicUtil.stop(MusicUtil.getMusicManager(trigger.getTextChannel()));
        trigger.getChannel().sendMessage(makeEmbed().addField("Stopped!", "All playing music has been stopped and the playlist has been cleared.", false).build()).queue();
    }
}