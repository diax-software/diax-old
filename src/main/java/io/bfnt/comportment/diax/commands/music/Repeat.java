package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 03/04/2017 at 22:40
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"repeat"}, guildOnly = true, description = "")
public class Repeat extends DiaxCommand {
    /**
     * A {@link DiaxCommand} that toggles if the song repeats or not.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Brazen
     */
    @Override
    public void execute(Message trigger) {
        //TODO: This command
    }
}