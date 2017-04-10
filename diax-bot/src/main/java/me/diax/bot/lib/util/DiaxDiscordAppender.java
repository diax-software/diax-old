package me.diax.bot.lib.util;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
import me.diax.bot.DiaxBot;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 * Created by Comportment on 10/04/2017.
 * If you don't understand this, we are screwed.
 */
public class DiaxDiscordAppender<E> extends UnsynchronizedAppenderBase<E> {

    private static long GUILD_ID = Long.valueOf(DiaxUtil.getGuildID());
    private static long CHANNEL_ID = Long.valueOf("300926282244554754");

    @Override
    protected void append(E eventObject) {
        TextChannel channel = DiaxBot.SHARDS[DiaxUtil.getShard(GUILD_ID)].getGuildById(GUILD_ID).getTextChannelById(CHANNEL_ID);
        channel.sendMessage(eventObject + "").queue();
        //DiaxUtil.splitByEvery(2000, eventObject + "");
        //channel::sendMessage(DiaxUtil.splitByEvery(2000, eventObject + ""));
    }
}