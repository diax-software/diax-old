package me.diax.bot.lib.util;

import me.diax.bot.DiaxBot;
import me.diax.bot.lib.audio.DiaxAudioTrack;
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
        return new EmbedBuilder().setColor(new Color(170, 101, 25)).setFooter(DiaxBot.VERSION, "https://cdn.discordapp.com/icons/293889712014360586/32a876d0e467fe7227f50e3bcfc45bd2.jpg");
    }

    public static EmbedBuilder simpleEmbed(String message) {
        return defaultEmbed().setDescription(message);
    }

    public static MessageEmbed errorEmbed(String message) {
        return defaultEmbed().setColor(new Color(111, 16, 10)).addField("⛔ Error ⛔", message, false).build();
    }

    public static MessageEmbed musicEmbed(String message) {
        return defaultEmbed().addField("\uD83C\uDFB5 Music \uD83C\uDFB5", message, false).build();
    }

    public static String getTrackInfo(DiaxAudioTrack track) {
        return String.format("`%s ` by `%s `\nRequested by `%s `", track.getTrack().getInfo().title, track.getTrack().getInfo().author, makeName(track.getRequester()));
    }

    public static String getOwnerID() {
        return "293884638101897216";
    }

    public static String getGuildID() {
        return "293889712014360586";
    }

    public static boolean checkPermission(User user, Guild guild, Permission permission) {
        return user.getId().equals(getOwnerID()) || PermissionUtil.checkPermission(guild, guild.getMember(user), permission);
    }

    public static int getShard(long guildID) {
        return (int) (guildID >> 22) % DiaxBot.SHARDS.length;
    }

    public static int getShard(Guild guild) {
        long id = Long.valueOf(guild.getId());
        return getShard(id);
    }

    public static String[] splitStringEvery(String s, int interval) {
        int size = (int) Math.ceil(((s.length() / (double) interval)));
        String[] result = new String[size];

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        }
        result[lastIndex] = s.substring(j);
        return result;
    }
}