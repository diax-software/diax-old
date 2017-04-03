package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.lib.music.GuildMusicManager;
import io.bfnt.comportment.diax.lib.music.MusicUtil;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;

/**
 * Created by Comporment on 02/04/2017 at 16:21
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"pause"}, permission = Permission.VOICE_MOVE_OTHERS, guildOnly = true, description = "Pauses the player for the guild.")
public class Pause extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which pauses the {@link GuildMusicManager} for the {@link Message#getGuild()}
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        GuildMusicManager manager = MusicUtil.getMusicManager(trigger.getTextChannel());
        if (!manager.player.isPaused()) {
            manager.player.setPaused(true);
            trigger.getChannel().sendMessage(makeEmbed().addField("Paused!", "Music has been paused for this guild!", false).build()).queue();
        } else {
            trigger.getChannel().sendMessage(makeEmbed().setColor(new Color(255, 0, 0)).addField("Error", "Music has already been paused.", false).build()).queue();
        }
    }
}
