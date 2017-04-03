package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 03/04/2017 at 19:43
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"playing", "song", "nowplaying"})
public class Playing extends DiaxCommand {
    /**
     *
     * @param trigger The {@link Message} which triggered the command.
     */
    public void execute(Message trigger) {

    }
}
