package io.bfnt.comportment.diax;

import io.bfnt.comportment.diax.lib.Diax;
import io.bfnt.comportment.diax.lib.Token;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Scanner;

/**
 * Created by Comporment on 22/03/2017 at 19:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class Main extends Diax
{

    /**
     * Method fired when the bot is started up.
     *
     * @param args Doesn't matter what they are, no usage.
     * @throws Exception JDABuilder will throw something.
     * @since Azote
     */
    public static void main(String[] args) throws Exception
    {
        System.out.print("Shard count > ");
        init((new Scanner(System.in)).nextInt() + 1);
    }

    /**
     * Method fired to start a JDA instance.
     *
     * @throws Exception Can throw multiple exceptions.
     * @since Azote
     */
    private static void init(int shards) throws Exception
    {
        for (int i = 0; i < shards; i++)
        {
            staticLog(String.format("Shard %d is starting.", i));
            JDA jda = new JDABuilder(AccountType.BOT).setToken(Token.mainToken()).useSharding(i, shards).setGame(Game.of(String.format("%shelp | Shard %s/%s", staticPrefix(), i, shards - 1))).addListener(new Main()).buildAsync();
            staticLog(String.format("Shard %d has been started.", i));
        }
    }

    /**
     * Test command to tell which shard Diax is on.
     *
     * @param event Method fired when bot receives a message.
     * @since Azote
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
       // if (event.getMessage().getRawContent().startsWith("<<")) event.getChannel().sendMessage("Shard ID: " + event.getJDA().getShardInfo().getShardId() + "/" + (event.getJDA().getShardInfo().getShardTotal() - 1)).queue();
    }
}