package io.bfnt.comportment.diax.lib;

import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    protected static void staticLog(String message)
    {
        System.out.println(new SimpleDateFormat("[HH:mm:ss] ").format(new Date()) + message);
    }

    /**
     * Logging from within normal methods.
     *
     * @param message Message to timestamp and then print to console.
     * @since Azote
     * @deprecated
     */
    @Deprecated
    protected void log(String message)
    {
        System.out.println(new SimpleDateFormat("[HH:mm:ss] ").format(new Date()) + message);
    }

    /**
     * Getting a static version of the prefix.
     *
     * @since Azote
     */
    protected static String staticPrefix()
    {
        return "<<";
    }

    /**
     * Getting the prefix.
     *
     * @since Azote
     * @deprecated
     */
    @Deprecated
    protected String getPrefix()
    {
        return "<<";
    }
}