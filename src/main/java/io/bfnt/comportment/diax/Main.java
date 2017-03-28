package io.bfnt.comportment.diax;

import io.bfnt.comportment.diax.lib.Diax;
import io.bfnt.comportment.diax.lib.Token;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
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
     */
    private static void init(int shards) throws Exception
    {
        for (int i = 0; i < shards; i++)
        {
            System.out.println("Starting shard: " + i);
            JDA jda = new JDABuilder(AccountType.BOT).setToken(Token.mainToken()).useSharding(i, shards + i).addListener(new Main()).buildAsync();
            System.out.println("Shard " + i + " started successfully.");
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getMessage().getRawContent().startsWith("<<")) event.getChannel().sendMessage(event.getJDA().getShardInfo().getShardId() + " shard.").queue();
    }
}