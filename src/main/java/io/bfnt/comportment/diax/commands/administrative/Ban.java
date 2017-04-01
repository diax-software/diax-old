package io.bfnt.comportment.diax.commands.administrative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.PermissionException;

/**
 * Created by Comporment on 30/03/2017 at 20:45
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"ban", "banne"}, minimumArgs = 1, permission = Permission.BAN_MEMBERS, description = "Bans the mentioned users from a guild.", guildOnly = true)
public class Ban extends DiaxCommand
{
    /**
     * A command which bans all of the mentioned {@link net.dv8tion.jda.core.entities.Member}s from the {@link net.dv8tion.jda.core.entities.Guild} the command was used in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger)
    {
        trigger.getMentionedUsers().forEach(user ->
        {
            try
            {
                trigger.getGuild().getController().ban(trigger.getGuild().getMember(user), 7).queue(_void
                        -> trigger.getChannel().sendMessage(makeEmbed().addField("Banned!", makeName(user) + " has been banned.", false).build()).queue());
            }
            catch (PermissionException e)
            {
                trigger.getChannel().sendMessage(makeEmbed().addField("Error!", "I could not ban " + makeName(user), false).build()).queue();
            }
        });
    }
}