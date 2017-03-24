package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 23/03/2017 at 21:45
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "kick", args = "@mention", emoji = "🔨", permission = Permission.KICK_MEMBERS, minimumArgs = 1)
public class Kick extends DiaxCommand
{
    public void execute(Message trigger)
    {
        try
        {
            Member member = trigger.getGuild().getMemberById(trigger.getRawContent().split(" ")[1].replaceAll("[<!@>]", ""));
            trigger.getGuild().getController().kick(member);
            trigger.getChannel().sendMessage(makeMessage("Kicked!", getNiceName(member.getUser()) + " has been kicked.").build()).queue();
        }
        catch (NullPointerException exception)
        {

        }
    }
}