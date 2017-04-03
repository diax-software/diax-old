package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.Commands;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;

import javax.inject.Inject;
import java.util.stream.Collectors;

import static io.bfnt.comportment.diax.util.Utils.makeEmbed;

/**
 * Created by Comporment on 28/03/2017 at 19:07
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"help", "?", "helpme"}, description = "Gives you help for Diax.")
public class Help extends DiaxCommand {

    private final Commands commands;

    @Inject
    public Help(Commands commands) {
        this.commands = commands;
    }

    /**
     * A command which displays all of the other registered {@link DiaxCommand} in the {@link io.bfnt.comportment.diax.lib.command.Commands} class.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @apiNote Method changed in Brazen to include injection.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        trigger.getChannel().sendMessage(makeEmbed().addField("Commands", commands.getCommands().stream()
                .sorted()
                .map(commands::newInstance)
                .filter(command -> !command.getOwnerOnly())
                .map(DiaxCommand::getHelpFormat)
                .collect(Collectors.joining("\n")), false).addField("Links", String.join("\n","[Invite me to your server](https://discordapp.com/oauth2/authorize?client_id=295500621862404097&scope=bot&permissions=8)", "[My Discord server](https://discord.gg/c6M8PJZ)", "[My Patreon](https://www.patreon.com/Diax)"), false).build()).queue();
    }
}