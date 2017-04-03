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
@CommandDescription(triggers = {"play", "queue", "unpause"}, permission = Permission.VOICE_CONNECT, guildOnly = true, description = "Queues the given track url.")
public class Play extends DiaxCommand {
    /**
     * {@link DiaxCommand} which plays the {@link com.sedmelluq.discord.lavaplayer.track.AudioTrack} given as an argument or unpauses the {@link GuildMusicManager}
     *
     * @param trigger The {@link Message} which triggered the command.
     */
    @Override
    public void execute(Message trigger) {
        GuildMusicManager manager = MusicUtil.getMusicManager(trigger.getTextChannel());
        if (trigger.getRawContent().split(" ").length <= 1) { //Not enough args for a track, person wants to unpause.
            if (manager.player.isPaused()) {
                manager.player.setPaused(false);
                trigger.getChannel().sendMessage(makeEmbed().addField("Unpaused!", "Music has been unpaused for this guild!", false).build()).queue();
            } else {
                trigger.getChannel().sendMessage(makeEmbed().setColor(new Color(255, 0, 0)).addField("Error", "Music has already been unpaused.", false).build()).queue();
            }
        }
        else {
            if (trigger.getGuild().getMember(trigger.getAuthor()).getVoiceState().inVoiceChannel()) {
                manager.player.setPaused(false);
                MusicUtil.loadAndPlay(manager, trigger.getGuild().getMember(trigger.getAuthor()).getVoiceState().getChannel(), trigger.getTextChannel(), trigger.getRawContent().split(" ")[1]);
            } else {
                trigger.getChannel().sendMessage(makeEmbed().addField("Error!", "You must be in a voice channel to use this command.", false).setColor(new Color(255, 0, 0)).build()).queue();
            }
        }
    }
}