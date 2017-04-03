package io.bfnt.comportment.diax.commands.informative;

import io.bfnt.comportment.diax.Main;
import io.bfnt.comportment.diax.lib.command.CommandDescription;
import io.bfnt.comportment.diax.lib.command.DiaxCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Comporment on 02/04/2017 at 10:27
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@CommandDescription(triggers = {"statistics", "stats"}, description = "Displays some statistics about the shard Diax is on.")
public class Statistics extends DiaxCommand {
    @Override
    public void execute(Message trigger) {
        List<JDA> shards;
        try {
            shards = Arrays.asList(new Main().getShards());
        } catch (NullPointerException e) {
            shards = Collections.singletonList(trigger.getJDA());
        }
        //trigger.getChannel().sendMessage(makeEmbed().setDescription().build()).queue();
        //       trigger.getChannel().sendMessage(makeEmbed().setDescription(String.format("Statistics for Shard #%d\n\n\uD83D\uDC65 Users: %s\n\uD83D\uDC64 Unique: %s\n\n\uD83D\uDCAF Version: %s", trigger.getJDA().getShardInfo().getShardId(), shards.forEach(s -> s.getGuilds().stream().mapToLong(guild -> guild.getMembers().size())) jda.getGuilds().stream().mapToLong(guild -> guild.getMembers().size()).sum(), jda.getUsers().stream().distinct().count(), getVersion())).build()).queue();
    }
}