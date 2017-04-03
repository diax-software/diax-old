package io.bfnt.comportment.diax.commands.owner;

import io.bfnt.comportment.diax.Main;
import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.Arrays;

/**
 * Created by Comporment on 03/04/2017 at 20:45
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"announce", "global"}, ownerOnly = true)
public class Announce extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which announces a message to all of the guilds Diax is in.
     *
     * @param trigger The {@link Message} which triggered the command.
     */
    public void execute(Message trigger) {
        String message = trigger.getRawContent().replaceFirst(trigger.getRawContent().split(" ")[0], "");
        Arrays.stream(Main.getShards()).flatMap(shard -> shard.getGuilds().stream()).forEach(guild -> {
            for (TextChannel channel : guild.getTextChannels()) {
                try {
                    channel.sendMessage(makeEmbed().setDescription(message).build()).queue();
                    break;
                } catch (Exception ignored) {}
            }}
        );
    }
}