package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 03/04/2017 at 19:34
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"whoami", "userinfo", "usercard", "profile"}, description = "Displays information about yourself.")
public class WhoAmI extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which will display the user card for the {@link Message#getAuthor()}
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger) {
        trigger.getChannel().sendMessage(makeEmbed().addField(makeName(trigger.getAuthor()) + "'s usercard.", "Coming soon!", false).build()).queue();
    }
}