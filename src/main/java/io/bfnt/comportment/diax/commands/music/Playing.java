package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.lib.music.GuildMusicManager;
import io.bfnt.comportment.diax.lib.music.MusicUtil;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 03/04/2017 at 19:43
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"playing", "song", "nowplaying"}, description = "Displays the currently playing track.", guildOnly = true)
public class Playing extends DiaxCommand {
    /**
     * A {@link DiaxCommand}
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger) {
        GuildMusicManager manager = MusicUtil.getMusicManager(trigger.getTextChannel());
        if (manager.player.getPlayingTrack() == null) {
            trigger.getChannel().sendMessage(makeEmbed().addField("Now Playing...", "Nothing.", false).build()).queue();
        } else {
            trigger.getChannel().sendMessage(MusicUtil.trackEmbed(MusicUtil.getMusicManager(trigger.getTextChannel()).player.getPlayingTrack())).queue();
        }
    }
}
