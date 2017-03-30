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
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.json.JSONException;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Comporment on 22/03/2017 at 19:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class Main extends Diax
{
    private static JDA[] shards;
    private static String login = Token.mainToken();

    /**
     * Method fired when the bot is started up.
     *
     * @param args Doesn't matter what they are, no usage.
     * @throws Exception JDABuilder will throw something.
     * @since Azote
     */
    public static void main(String[] args) throws Exception
    {
        int recommendedShards = getRecommendedShards();
        log(String.format("Starting with %d shard(s).", recommendedShards));
        init(recommendedShards);
        List<JDA> jdas = Arrays.asList(shards);
        log("Unique users on startup: " + jdas.stream().flatMap(shard -> shard.getUsers().stream().distinct()).count());
        log("Guilds on startup: " +  jdas.stream().flatMap(shard -> shard.getGuilds().stream()).distinct().count());
    }

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
                    .header("Authorization", "Bot " + login)
                    .header("Content-Type", "application/json").asJson();
            return Integer.parseInt(request.getBody().getObject().get("shards").toString());
        }
        catch (UnirestException|JSONException e)
        {
            e.printStackTrace();
        }
        return 1;
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
                JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(login).setGame(Game.of(getPrefix() + "help | Shards: " + amount)).addListener(new CommandHandler());
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
                log(String.format("Shard %d has been loaded successfully.", i + 1));
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
        log(String.format("Finished loading with %d shard(s).", amount));
    }
}