package io.bfnt.comportment.diax.lib;

import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.utils.PermissionUtil;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Comporment on 28/03/2017 at 12:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Diax extends ListenerAdapter
{
    private TreeSet<DiaxCommand> commands = new TreeSet<>();

    /**
     * Logging from within static methods.
     *
     * @param message Message to timestamp and then print to console.
     * @since Azote
     */
    protected static void log(String message)
    {
        System.out.println(String.format("%s [Log] %s", new SimpleDateFormat("[HH:mm:ss]").format(new Date()), message));
    }

    /**
     * Method to get a static version of the prefix.
     *
     * @since Azote
     */
    protected static String getPrefix()
    {
        return "<<";
    }

    /**
     * Method to get the commands currently being used.
     *
     * @return A TreeSet containing all of the {@link DiaxCommand}s which can be used.
     * @since Azote
     */
    protected TreeSet<DiaxCommand> getCommands()
    {
        return commands;
    }

    /**
     * Method that should only be called once and is used to register all of the {@link DiaxCommand}s that can be used.
     *
     * @param diaxCommands Tbe {@link DiaxCommand}s to be registered.
     * @since Azote
     */
    protected void registerCommands(DiaxCommand... diaxCommands)
    {
        commands.clear();
        Arrays.stream(diaxCommands).forEach(command ->
        {
            log("Registering: " + command.getTrigger());
            commands.add(command);
        });
    }

    /**
     * Method to have a global way to make a {@link net.dv8tion.jda.core.entities.MessageEmbed}
     *
     * @return A {@link EmbedBuilder} containing Diax's defaults for the {@link net.dv8tion.jda.core.entities.MessageEmbed}
     */
    protected EmbedBuilder makeEmbed()
    {
        return new EmbedBuilder().setColor(new Color(114,137,218));
    }

    /**
     * Method to get a less ugly version of the {@link User}'s name, as displayed in the client.
     *
     * @param user The {@link User}'s name to convert into a nicer name.
     * @return A string in the format: username#discriminator
     */
    protected String makeName(User user)
    {
        return String.format("%s#%s", user.getName(), user.getDiscriminator());
    }

    /**
     * Returns true if the {@link User} has the permission in the guild to use the command, or is Comportment himself.
     *
     * @param user The {@link User} to check the permission of.
     * @param guild The {@link Guild} to see if the user has the permission there.
     * @param permission The {@link Permission} to check.
     * @return If the {@link User} is Comportment or has the required {@link Permission}
     */
    protected boolean checkPermission(User user, Guild guild, Permission permission)
    {
        return user.getId().equals("293884638101897216") | PermissionUtil.checkPermission(guild, guild.getMember(user), permission);
    }
}