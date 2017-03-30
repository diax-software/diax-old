package io.bfnt.comportment.diax.lib.command;

import io.bfnt.comportment.diax.lib.Diax;
import net.dv8tion.jda.core.Permission;

/**
 * Created by Comporment on 28/03/2017 at 16:48
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class DiaxCommand extends Diax implements Command, Comparable<DiaxCommand>
{
    /**
     * Method to get the CommandDescription annotation.
     *
     * @return sometimes null, the {@link CommandDescription} annotation of the DiaxCommand.
     * @since Azote
     */
    private CommandDescription getCommandDescription()
    {
        return getClass().getAnnotation(CommandDescription.class);
    }

    /**
     * Method to get if the {@link DiaxCommand} can be used in a guild or not as specified in the {@link CommandDescription}
     *
     * @return Whether or not the command can be used in guilds.
     * @since Azote
     */
    public boolean getGuildOnly()
    {
        return getCommandDescription().guildOnly();
    }

    /**
     * Method to get the Strings which will trigger the {@link DiaxCommand} as specified in the {@link CommandDescription}
     *
     * @return An array of the Strings which will trigger the {@link DiaxCommand}
     * @since Azote
     */
    public String[] getTriggers()
    {
        return getCommandDescription().triggers();
    }

    /**
     * Method to get the Strings which will trigger the {@link DiaxCommand} as specified in the {@link CommandDescription}
     *
     * @return A string containing the description of the {@link DiaxCommand}
     * @since Azote
     */
    public String getDescription()
    {
        return getCommandDescription().description();
    }

    /**
     * Method to return the minimum required {@link Permission} to use the {@link DiaxCommand} as specified in the
     *
     * @return the minimum required {@link Permission} to use the command in a {@link net.dv8tion.jda.core.entities.Guild}
     * @since Azote
     */
    public Permission getPermission()
    {
        return getCommandDescription().permission();
    }

    /**
     * Method to return the minimum amount of args to use the {@link DiaxCommand} as specified in the {@link CommandDescription}
     *
     * @return An int which has the minimum amount of args to use the {@link DiaxCommand}
     * @since Azote
     */
    public int getMinimumArgs()
    {
        return getCommandDescription().minimumArgs();
    }

    /**
     * Method to get the main String which will trigger the {@link DiaxCommand} (The first item from {@link #getMinimumArgs()}
     *
     * @return A string which will mainly be used to trigger the {@link DiaxCommand}
     * @since Azote
     */
    public String getTrigger()
    {
        return getTriggers()[0];
    }

    /**
     * Method to see what order the commands should go in when sorted.
     *
     * @param command {@link DiaxCommand} to compare the to order.
     * @return an int describing the order that the command goes in.
     * @since Azote
     */
    public int compareTo(DiaxCommand command)
    {
        return (getTrigger() + getDescription()).compareTo(command.getTrigger() + command.getDescription());
    }


    /**
     * Method to get the format of the command as used in {@link io.bfnt.comportment.diax.commands.Help}
     *
     * @return A string containing the format of the command used in {@link io.bfnt.comportment.diax.commands.Help}
     */
    public String getHelpFormat()
    {
        return String.format("%s%s | %s", getPrefix(), getTrigger(), getDescription());
    }
}
