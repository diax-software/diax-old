package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.lib.music.GuildMusicManager;
import io.bfnt.comportment.diax.lib.music.MusicUtil;
import net.dv8tion.jda.core.entities.Message;

import static io.bfnt.comportment.diax.util.Utils.makeEmbed;

/**
 * Created by Comporment on 03/04/2017 at 19:43
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"song", "nowplaying"}, description = "Displays the currently playing track.", guildOnly = true)
public class Song extends DiaxCommand {
    /**
     * A {@link DiaxCommand} that attempts to play the argument.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @author Comportment
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        GuildMusicManager manager = MusicUtil.getMusicManager(trigger.getTextChannel());
        if (manager.player.getPlayingTrack() == null) {
            trigger.getChannel().sendMessage(makeEmbed().addField("Now Playing...", "Nothing.", false).build()).queue();
        } else {
            trigger.getChannel().sendMessage(MusicUtil.trackEmbed(MusicUtil.getMusicManager(trigger.getTextChannel()).player.getPlayingTrack())).queue();
        }
    }
}
