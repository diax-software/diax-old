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
@CommandDescription(triggers = {"unpause"}, permission = Permission.VOICE_CONNECT, guildOnly = true, minimumArgs = 1, description = "Pauses the player")
public class Unpause extends DiaxCommand {
    @Override
    public void execute(Message trigger) {
        GuildMusicManager manager = MusicUtil.getMusicManager(trigger.getTextChannel());
        if (manager.player.isPaused()) {
            manager.player.setPaused(false);
            trigger.getChannel().sendMessage(makeEmbed().addField("Success!", "Music has been unpaused", false).build()).queue();
        } else {
            trigger.getChannel().sendMessage(makeEmbed().setColor(new Color(255, 0, 0)).addField("Error", "Music is already unpasued", false).build()).queue();
        }
    }
}
