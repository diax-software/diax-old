package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 26/03/2017 at 11:41
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(minimumArgs = 1, name = "play", guildOnly = true, permission = Permission.VOICE_MOVE_OTHERS, args = "YouTube URL", emoji = "🎧")
public class Play extends DiaxCommand
{
    public void execute(Message trigger)
    {
        loadAndPlay(trigger.getTextChannel(), trigger.getRawContent().split(" ")[1]);
    }
}