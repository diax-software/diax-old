package io.bfnt.comportment.diax.lib.command;

import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 28/03/2017 at 16:46
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@FunctionalInterface
public interface Command {
    /**
     * Method that is fired when a {@link DiaxCommand} is being executed.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @param truncated A String containing the message without the args.
     * @apiNote This Method was changed in Brazen to include the truncated arg to allow for multiple prefixes.
     * @author Comportment
     * @since Azote
     */
    void execute(Message trigger, String truncated);
}