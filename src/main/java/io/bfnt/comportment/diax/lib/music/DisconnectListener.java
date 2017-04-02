package io.bfnt.comportment.diax.lib.music;

import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by Comporment on 02/04/2017 at 16:17
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DisconnectListener extends ListenerAdapter
{

    /**
     * A method that is fired when a {@link net.dv8tion.jda.core.entities.Guild} becomes unavailable.
     *
     * @param event The {@link GuildUnavailableEvent} which was fired.
     * @since Azote
     */
    @Override
    public void onGuildUnavailable(GuildUnavailableEvent event)
    {
        event.getGuild().getAudioManager().closeAudioConnection();
    }

    /**
     * A method that is fired when Diax is removed from a {@link net.dv8tion.jda.core.entities.Guild}
     *
     * @param event The {@link GuildLeaveEvent} which was fired.
     * @since Azote
     */
    @Override
    public void onGuildLeave(GuildLeaveEvent event)
    {
        event.getGuild().getAudioManager().closeAudioConnection();
    }

    /**
     * A method that is fired when a {@link net.dv8tion.jda.core.entities.Member} is moved from one {@link net.dv8tion.jda.core.entities.VoiceChannel} to another {@link net.dv8tion.jda.core.entities.VoiceChannel}
     *
     * @param event The {@link GuildVoiceMoveEvent} which was fired.
     * @since Azote
     */
    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event)
    {
        if (event.getChannelLeft().getMembers().contains(event.getGuild().getMember(event.getJDA().getSelfUser())) && event.getChannelLeft().getMembers().size() < 2) event.getChannelLeft().getGuild().getAudioManager().closeAudioConnection();
        if (event.getChannelJoined().getMembers().contains(event.getGuild().getMember(event.getJDA().getSelfUser())) && event.getChannelJoined().getMembers().size() < 2) event.getChannelJoined().getGuild().getAudioManager().closeAudioConnection();
    }

    /**
     * A method that is fired when a {@link net.dv8tion.jda.core.entities.Member} leaves a {@link net.dv8tion.jda.core.entities.VoiceChannel}
     *
     * @param event The {@link GuildVoiceLeaveEvent} which was fired.
     * @since Azote
     */
    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        if (event.getChannelLeft().getMembers().contains(event.getGuild().getMember(event.getJDA().getSelfUser())) && event.getChannelLeft().getMembers().size() < 2) event.getChannelLeft().getGuild().getAudioManager().closeAudioConnection();
    }
}