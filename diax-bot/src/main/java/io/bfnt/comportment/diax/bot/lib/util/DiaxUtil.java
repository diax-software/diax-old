package io.bfnt.comportment.diax.bot.lib.util;

import io.bfnt.comportment.diax.bot.Diax;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.utils.PermissionUtil;

import java.awt.*;

/**
 * Created by Comporment on 04/04/2017 at 22:55
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxUtil {

    public static String makeName(User user) {
        return String.format("%s#%s", user.getName(), user.getDiscriminator());
    }

    public static EmbedBuilder defaultEmbed() {
        return new EmbedBuilder().setColor(new Color(170, 101, 25)).setFooter(Diax.VERSION, "https://google.com");
    }

    public static MessageEmbed errorEmbed(String message) {
        return defaultEmbed().setColor(new Color(111, 16, 10)).addField("⛔ Error!", message, false).build();
    }

    public static MessageEmbed musicEmbed(String message) {
        return defaultEmbed().addField("\uD83C\uDFB5 Music! ", message, false).build();
    }

    public static String getOwnerID() {
        return "293884638101897216";
    }

    public static boolean checkPermission(User user, Guild guild, Permission permission) {
        return user.getId().equals(getOwnerID()) || PermissionUtil.checkPermission(guild, guild.getMember(user), permission);
    }
}