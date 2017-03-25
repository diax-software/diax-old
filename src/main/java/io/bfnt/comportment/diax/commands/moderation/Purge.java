package io.bfnt.comportment.diax.commands.moderation;

import io.bfnt.comportment.diax.api.command.ModerationCommand;
import io.bfnt.comportment.diax.api.command.Punishment;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 25/03/2017 at 11:41
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Purge extends ModerationCommand
{
    public void execute(Message trigger)
    {
        Member member = getMemberFromString(trigger.getRawContent().split(" ")[1], trigger.getGuild());
        punish(member, trigger.getChannel(), Punishment.PURGE);
    }
}