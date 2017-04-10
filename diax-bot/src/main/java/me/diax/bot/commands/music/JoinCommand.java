package me.diax.bot.commands.music;

import me.diax.bot.lib.command.DiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.util.DiaxUtil;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.PermissionException;

/**
 * Created by Comportment on 10/04/2017.
 * If you don't understand this, we are screwed.
 */
@DiaxCommandDescription(triggers = {"join", "connect"}, permission = Permission.VOICE_MOVE_OTHERS, guildOnly = true)
public class JoinCommand extends DiaxCommand {

    @Override
    public void execute(Message trigger, String args) {
        GuildVoiceState voiceState = trigger.getGuild().getMember(trigger.getAuthor()).getVoiceState();
        TextChannel channel = trigger.getTextChannel();
        if (! voiceState.inVoiceChannel()) {
            channel.sendMessage(DiaxUtil.errorEmbed("You must be in a voice channel to do this!")).queue();
            return;
        }
        try {
            trigger.getGuild().getAudioManager().openAudioConnection(voiceState.getChannel());
            channel.sendMessage(DiaxUtil.musicEmbed("I have joined your voice channel.")).queue();
        } catch (PermissionException exception) {
            channel.sendMessage("I could not join your voice channel.").queue();
        }
    }
}