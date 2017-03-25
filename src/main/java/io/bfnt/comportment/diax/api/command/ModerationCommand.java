package io.bfnt.comportment.diax.api.command;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.managers.GuildController;

/**
 * Created by Comporment on 25/03/2017 at 09:26
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class ModerationCommand extends DiaxCommand
{
    protected void punish(Message trigger, Punishment punishment)
    {
        Member member = getMemberFromString(trigger.getRawContent().split(" ")[1], trigger.getGuild());
        if (member == null)
        {
            makeError(trigger.getChannel(), ErrorType.USER_NOT_FOUND);
            return;
        }
        GuildController c = member.getGuild().getController();
        try
        {
            switch (punishment)
            {
                case BAN:
                {
                    c.ban(member, 7).queue();
                    break;
                }
                case KICK:
                {
                    c.kick(member).queue();
                    break;
                }
                case SOFT_BAN:
                {
                    c.ban(member, 7).queue(_void -> c.unban(member.getUser()).queue());
                    break;
                }
                case PURGE:
                {
                    channel.getHistory().retrievePast(100).queue(history ->
                            channel.deleteMessages(history).queue(_void ->
                                    channel.sendMessage(makeMessage("Purged", "100 messages have been purged.").build()).queue()));
                    return;
                }
            }
            channel.sendMessage(makeMessage(punishment.getPast(), String.format("%s has been %s.", getNiceName(member.getUser()), punishment.getPast())).build()).queue();
        }
        catch (PermissionException e)
        {
            makeError(channel, ErrorType.SELF_NO_PERMISSION);
        }

    }
}