package io.bfnt.comportment.diax.commands.information;

import io.bfnt.comportment.diax.api.command.CommandDescription;
import io.bfnt.comportment.diax.api.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 26/03/2017 at 14:52
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(name = "invite", emoji = "📧")
public class Invite extends DiaxCommand
{
    public void execute(Message trigger)
    {
        trigger.getChannel().sendMessage(makeMessage("Invite Me!", "Invite me to your server using these links:\nhttps://discordapp.com/oauth2/authorize?client_id=295500621862404097&scope=bot&permissions=3148801 <-- Basic required permissions\nhttps://discordapp.com/oauth2/authorize?client_id=295500621862404097&scope=bot&permissions=3148815 <-- Administrative commands\n\nhttps://discordapp.com/oauth2/authorize?client_id=295500621862404097&scope=bot&permissions=-1 <-- You decide.").build()).queue();
    }
}
