package io.bfnt.comportment.diax.commands;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 30/03/2017 at 17:23
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"embed", "eb", "announce", "echo"}, description = "Embeds a message into a chat.", permission = Permission.MESSAGE_EMBED_LINKS, minimumArgs = 1)
public class Embed extends DiaxCommand
{
    /**
     * The embed command which embeds the content of the given message.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    public void execute(Message trigger)
    {
        trigger.delete().queue(_void
                -> trigger.getChannel().sendMessage(makeEmbed().setAuthor(makeName(trigger.getAuthor()), trigger.getAuthor().getEffectiveAvatarUrl(), trigger.getAuthor().getEffectiveAvatarUrl()).setDescription(trigger.getRawContent().replaceFirst(trigger.getRawContent().split(" ")[0], "")).build()).queue());
    }
}