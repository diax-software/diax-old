package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.Main;
import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static io.bfnt.comportment.diax.util.Utils.makeEmbed;

/**
 * Created by Comporment on 02/04/2017 at 10:27
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"statistics", "stats"}, description = "Displays some statistics about the shard Diax is on.")
public class Statistics extends DiaxCommand {
    /**
     * A {@link DiaxCommand} which gets statistics from cross-shard instances.
     *
     * @param trigger The {@link Message} which triggered the command.
     * @apiNote Rewrote in Brazen to look more pleasing in the source.
     * @since Azote
     */
    @Override
    public void execute(Message trigger) {
        List<JDA> shards = Arrays.asList(Main.getShards());
        String users = shards.stream().mapToLong(shard -> shard.getUsers().size()).sum() + "";
        String unique = shards.stream().mapToLong(shard -> shard.getUsers().stream().distinct().count()).sum() + "";
        String guilds = shards.stream().mapToLong(shard -> shard.getGuilds().size()).sum() + "";
        String _message = MessageFormat.format(
                "Statistics for Diax!\nUsers: {1}\nUnique: {2}\nGuilds: {3}",
                users, unique, guilds);

        trigger.getChannel().sendMessage(makeEmbed().setDescription(_message)
                .build())
                .queue();
    }
}