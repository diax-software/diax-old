package io.bfnt.comportment.diax.commands.moderation;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.ErrorType;
import io.bfnt.comportment.diax.api.command.ModerationCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 23/03/2017 at 21:45
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "kick", args = "@mention", emoji = "👞", permission = Permission.KICK_MEMBERS, minimumArgs = 1, guildOnly = true)
public class Kick extends ModerationCommand
{
    public void execute(Message trigger)
    {
        try
        {
            Member member = getMemberFromString(trigger.getRawContent().split(" ")[1], trigger.getGuild());
            trigger.getGuild().getController().ban(member, 7).queue(_void ->
                    trigger.getChannel().sendMessage(makeMessage("Kicked!", getNiceName(member) + " has been kicked.").build()).queue());
        }
        catch (NullPointerException e)
        {
            makeError(trigger.getChannel(), ErrorType.USER_NOT_FOUND);
        }
    }
}