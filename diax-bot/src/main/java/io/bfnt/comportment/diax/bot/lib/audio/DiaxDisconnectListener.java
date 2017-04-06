package io.bfnt.comportment.diax.bot.lib.audio;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by Comporment on 04/04/2017 at 22:03
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxDisconnectListener extends ListenerAdapter {

    @Override
    public void onGuildUnavailable(GuildUnavailableEvent event) {
        close(event.getGuild());
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        close(event.getGuild());
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        if ((!event.getChannelLeft().getMembers().contains(event.getGuild().getMember(event.getJDA().getSelfUser())) && event.getChannelLeft().getMembers().size() < 2) || (!event.getChannelJoined().getMembers().contains(event.getGuild().getMember(event.getJDA().getSelfUser())) && event.getChannelJoined().getMembers().size() < 2))
            return;
        close(event.getGuild());
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        if (!event.getChannelLeft().getMembers().contains(event.getGuild().getMember(event.getJDA().getSelfUser())) && event.getChannelLeft().getMembers().size() < 2)
            close(event.getGuild());
    }

    private void close(Guild guild) {
        guild.getAudioManager().closeAudioConnection();
    }
}