package io.bfnt.comportment.diax.commands.miscellaneous;

import io.bfnt.comportment.diax.Main;
import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 02/04/2017 at 17:53
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"bump"}, description = "Bumps the server on the DiaxList", guildOnly = true, permission = Permission.CREATE_INSTANT_INVITE)
public class Bump extends DiaxCommand {
    /**
     * A command which bumps the {@link net.dv8tion.jda.core.entities.Guild} the {@link Message} was send in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        long guildid = 293889712014360586L;
        trigger.getGuild().getPublicChannel().createInvite().queue(invite -> Main.getShards()[(int) ((guildid >> 22) % trigger.getJDA().getShardInfo().getShardTotal())].getGuildById(guildid + "").getTextChannelById("294519934996971520").sendMessage(invite.getCode()).queue());
    }
}