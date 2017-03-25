package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.music.Music;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 25/03/2017 at 22:56
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "music", minimumArgs = 1, permission = Permission.VOICE_CONNECT, args = "song url", guildOnly = true, emoji = "🎵")
public class Play extends Music
{
    public void execute(Message trigger)
    {
        loadAndPlay(trigger.getTextChannel(), trigger.getRawContent().split(" ")[1]);
    }
}
