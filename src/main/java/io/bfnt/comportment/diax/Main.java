package io.bfnt.comportment.diax;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.bfnt.comportment.diax.lib.Diax;
import io.bfnt.comportment.diax.lib.Token;
import io.bfnt.comportment.diax.lib.command.CommandHandler;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by Comporment on 22/03/2017 at 19:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class Main extends Diax
{

    static JDA[] shards;

    /**
     * Method to query Discord to see how many shards the bot should use when making a {@link JDA}
     *
     * @return the amount of shards the bot should use.
     * @since Azote
     */
    private static int getRecommendedShards()
    {
        try
        {
            HttpResponse<JsonNode> request = Unirest.get("https://discordapp.com/api/gateway/bot")
                    .header("Authorization", "Bot " + Token.mainToken())
                    .header("Content-Type", "application/json")
                    .asJson();
            return Integer.parseInt(request.getBody().getObject().get("shards").toString());
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * Method fired when the bot is started up.
     *
     * @param args Doesn't matter what they are, no usage.
     * @throws Exception JDABuilder will throw something.
     * @since Azote
     */
    public static void main(String[] args) throws Exception
    {
        int shards = getRecommendedShards();
        log(String.format("Starting with %d shard(s).", shards));
        init(shards);
    }

    /**
     * Method fired to start a JDA instance, uses sharding if needed.
     *
     * @since Azote
     */
    private static void init(int amount)
    {
        shards = new JDA[amount];

        for (int i = 0; i < amount; i++)
        {
            JDA jda = null;
            try
            {
                JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(Token.mainToken()).setGame(Game.of(getPrefix() + "help | Shards: " + amount)).addListener(new CommandHandler());

                if (amount > 1)
                {
                    jda = builder.useSharding(i, amount).buildBlocking();
                }
                else
                {
                    jda = builder.buildBlocking();
                }
            }
            catch (LoginException|RateLimitedException|InterruptedException exception)
            {
                exception.printStackTrace();
            }
            if (jda != null)
            {
                shards[i] = jda;
                log("Loaded shard: " + i);
            }
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
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