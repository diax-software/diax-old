package io.bfnt.comportment.diax.commands.music;

import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.Message;

import static io.bfnt.comportment.diax.util.Utils.makeEmbed;

/**
 * Created by Comporment on 03/04/2017 at 19:00
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"join", "connect", "move"}, description = "Makes Diax join the voice channel you are in.", permission = Permission.VOICE_MOVE_OTHERS, guildOnly = true)
public class Join extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which makes Diax join the {@link net.dv8tion.jda.core.entities.VoiceChannel} the {@link Message#getAuthor()} is in.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        GuildVoiceState state = trigger.getGuild().getMember(trigger.getAuthor()).getVoiceState();
        if (state.inVoiceChannel()) {
            trigger.getGuild().getAudioManager().openAudioConnection(state.getChannel());
            trigger.getChannel().sendMessage(makeEmbed().addField("Moved!", "I have joined your voice channel!", false).build()).queue();
        } else {
            trigger.getChannel().sendMessage(makeEmbed().addField("Error!", "You must be in a voice channel first!", false).build()).queue();
        }
    }
}
