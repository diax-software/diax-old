package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.Main;
import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;

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
        trigger.getChannel().sendMessage(makeEmbed().setDescription(String.format("Statistics for Diax!\n\nUsers: %s\n\nUnique: %s\n\nGuilds: %s", shards.stream().mapToLong(shard -> shard.getUsers().size()).sum(), shards.stream().mapToLong(shard -> shard.getUsers().stream().distinct().count()).sum(), shards.stream().mapToLong(shard -> shard.getGuilds().size()).sum())).build()).queue();
    }
}