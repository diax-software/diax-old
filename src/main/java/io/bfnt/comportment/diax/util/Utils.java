package io.bfnt.comportment.diax.util;

import io.bfnt.comportment.diax.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.utils.PermissionUtil;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    /**
     * Logging from within static methods.
     *
     * @param message Message to timestamp and then print to console.
     * @since Azote
     */
    public static void log(String message) {
        System.out.println(String.format("%s [Log] %s", new SimpleDateFormat("[HH:mm:ss]").format(new Date()), message));
    }

    /**
     * Method to have a global way to make a {@link net.dv8tion.jda.core.entities.MessageEmbed}
     *
     * @return A {@link EmbedBuilder} containing Diax's defaults for the {@link net.dv8tion.jda.core.entities.MessageEmbed}
     * @since Azote
     */
    public static EmbedBuilder makeEmbed() {
        return new EmbedBuilder().setColor(new Color(114, 137, 218)).setFooter(getVersion(), "https://cdn.discordapp.com/avatars/295500621862404097/07aa17a7391dbec5c3490e4975cc40e7.webp?size=1024");
    }

    /**
     * Method to get a less ugly version of the {@link User}'s name, as displayed in the client.
     *
     * @param user The {@link User}'s name to convert into a nicer name.
     * @return A string in the format: username#discriminator
     * @since Azote
     */
    public static String makeName(User user) {
        return String.format("%s#%s", user.getName(), user.getDiscriminator());
    }

    /**
     * Returns true if the {@link User} has the permission in the guild to use the command, or is Comportment himself.
     *
     * @param user       The {@link User} to check the permission of.
     * @param guild      The {@link Guild} to see if the user has the permission there.
     * @param permission The {@link Permission} to check.
     * @return If the {@link User} is Comportment or has the required {@link Permission}
     */
    public static boolean checkPermission(User user, Guild guild, Permission permission) {
        return user.getId().equals("293884638101897216") | PermissionUtil.checkPermission(guild, guild.getMember(user), permission);
    }

    /**
     * A method to get the version number and name of Diax.
     *
     * @return The version name and number of the current version of Diax.
     * @since Azote
     */
    public static String getVersion() {
        return Main.VERSION;
    }

    /**
     * Method to get the ID of Comportment (the bot owner)
     *
     * @return The ID of Comportment as described in {@link ISnowflake#getId()}
     * @since Azote
     */
    public static String getOwnerId() {
        return "293884638101897216";
    }
}
