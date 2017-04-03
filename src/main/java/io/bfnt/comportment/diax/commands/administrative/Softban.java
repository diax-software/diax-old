package io.bfnt.comportment.diax.commands.administrative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.PermissionException;

import static io.bfnt.comportment.diax.util.Utils.makeEmbed;
import static io.bfnt.comportment.diax.util.Utils.makeName;

/**
 * Created by Comporment on 30/03/2017 at 21:32
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"softban"}, description = "Soft-bans the mentioned users from the guild.", guildOnly = true, permission = Permission.KICK_MEMBERS, minimumArgs = 1)
public class Softban extends DiaxCommand {
    /**
     * A command which bans, and then unbans all of the mentioned {@link net.dv8tion.jda.core.entities.Member}s from the {@link net.dv8tion.jda.core.entities.Guild} the command was used in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        trigger.getMentionedUsers().forEach(user ->
        {
            try {
                trigger.getGuild().getController().ban(trigger.getGuild().getMember(user), 7).queue(_void
                        -> trigger.getGuild().getController().unban(user).queue(__void
                        -> trigger.getChannel().sendMessage(makeEmbed().addField("Softbanned!", makeName(user) + " has been softbanned.", false).build()).queue()));
            } catch (PermissionException e) {
                trigger.getChannel().sendMessage(makeEmbed().addField("Error!", "I could not softban " + makeName(user), false).build()).queue();
            }
        });
    }
}