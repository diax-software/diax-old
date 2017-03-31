package io.bfnt.comportment.diax.commands.administrative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.PermissionException;

/**
 * Created by Comporment on 30/03/2017 at 21:19
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"kick"}, minimumArgs = 1, permission = Permission.KICK_MEMBERS, description = "Kicks the mentioned users from a guild.", guildOnly = true)
public class Kick extends DiaxCommand
{
    public void execute(Message trigger)
    {
        trigger.getMentionedUsers().forEach(user ->
        {
            try
            {
                trigger.getGuild().getController().kick(trigger.getGuild().getMember(user)).queue(_void
                        -> trigger.getChannel().sendMessage(makeEmbed().addField("Kicked!", makeName(user) + " has been kicked.", false).build()).queue());
            }
            catch (PermissionException e)
            {
                trigger.getChannel().sendMessage(makeEmbed().addField("Error!", "I could not kick " + makeName(user), false).build()).queue();
            }
        });
    }
}