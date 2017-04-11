package me.diax.bot.commands.statistics;

import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.command.DiaxCommands;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.entities.Message;

import javax.inject.Inject;
import java.util.stream.Collectors;

/**
 * Created by Comportment on 11/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"help", "?", "helpme"})
public class Help extends DiaxCommand {

    private final DiaxCommands commands;

    @Inject
    public Help(DiaxCommands commands) {
        this.commands = commands;
    }

    @Override
    public void execute(Message trigger, String args) {
        trigger.getChannel().sendMessage(DiaxUtil.defaultEmbed().addField("Commands", commands.getCommands().stream()
                //.sorted()
                .map(commands::newInstance)
                .filter(command -> ! command.getOwnerOnly())
                .map(DiaxCommand::getHelpFormat)
                .collect(Collectors.joining("\n")), false).addField("Links", String.join("\n", "[Invite me to your server](https://discordapp.com/oauth2/authorize?client_id=295500621862404097&scope=bot&permissions=8)", "[My Discord server](https://discord.gg/c6M8PJZ)", "[My Patreon](https://www.patreon.com/Diax)"), false).build()).queue();
    }
}