package me.diax.bot.lib.command;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;


/**
 * Created by Comporment on 04/04/2017 at 22:47
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class DiaxCommand implements DiaxCommandInterface, Comparable<DiaxCommand> {

    //@Inject
    //@Named(value = "me.diax.commands.prefix")
    private String prefix = "<<";

    public abstract void execute(Message trigger, String truncated);

    private DiaxCommandDescription getCommandDescription() {
        return getClass().getAnnotation(DiaxCommandDescription.class);
    }

    public String[] getTriggers() {
        return getCommandDescription().triggers();
    }

    public String getDescription() {
        return getCommandDescription().description();
    }

    public Permission getPermission() {
        return getCommandDescription().permission();
    }

    public int getMinimumArgs() {
        return getCommandDescription().minimumArgs();
    }

    public String getTrigger() {
        return getTriggers()[0];
    }

    public int compareTo(DiaxCommand command) {
        return (getTrigger() + getDescription()).compareTo(command.getTrigger() + command.getDescription());
    }

    public String getHelpFormat() {
        return String.format("%s%s | %s", prefix, getTrigger(), getDescription());
    }

    public boolean getOwnerOnly() {
        return getCommandDescription().ownerOnly();
    }

    public boolean getGuildOnly() {
        return getCommandDescription().guildOnly();
    }

    public boolean getDonorOnly() {
        return getCommandDescription().donorOnly();
    }
}