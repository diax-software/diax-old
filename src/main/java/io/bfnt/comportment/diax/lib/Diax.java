package io.bfnt.comportment.diax.lib;

import io.bfnt.comportment.diax.commands.Help;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Comporment on 28/03/2017 at 12:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Diax extends ListenerAdapter
{
    /**
     * Logging from within static methods.
     *
     * @param message Message to timestamp and then print to console.
     * @since Azote
     */
    protected static void log(String message)
    {
        System.out.println(new SimpleDateFormat("[HH:mm:ss] ").format(new Date()) + message);
    }

    /**
     * Logging from within normal methods.
     *
     * @param message Message to timestamp and then print to console.
     * @since Azote
     * @deprecated use {@link #log(String)} instead.
     */
    @Deprecated
    protected void oldLog(String message)
    {
        System.out.println(new SimpleDateFormat("[HH:mm:ss] ").format(new Date()) + message);
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
     * Method to get the prefix that is used to trigger a {@link DiaxCommand}
     *
     * @since Azote
     * @deprecated Use {@link #getPrefix()} instead
     */
    @Deprecated
    protected String oldPrefix()
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
        return new TreeSet<DiaxCommand>()
        {{
            add(new Help());
        }};
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
}