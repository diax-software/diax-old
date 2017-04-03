package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import io.bfnt.comportment.diax.lib.music.MusicUtil;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;

/**
 * Created by Comporment on 02/04/2017 at 16:21
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"play", "queue"}, permission = Permission.VOICE_CONNECT, guildOnly = true, minimumArgs = 1, description = "Queues the given track url.")
public class Play extends DiaxCommand
{
    @Override
    public void execute(Message trigger)
    {
        if (trigger.getGuild().getMember(trigger.getAuthor()).getVoiceState().inVoiceChannel())
        {
            trigger.getGuild().getAudioManager().openAudioConnection(trigger.getGuild().getMember(trigger.getAuthor()).getVoiceState().getChannel());
            MusicUtil.loadAndPlay(MusicUtil.getMusicManager(trigger.getTextChannel()), trigger.getTextChannel(), trigger.getRawContent().split(" ")[1]);
        }
        else
        {
            trigger.getChannel().sendMessage(makeEmbed().addField("Error!", "You must be in a voice channel to use this command.", false).setColor(new Color(255, 0, 0)).build()).queue();
        }
    }
}