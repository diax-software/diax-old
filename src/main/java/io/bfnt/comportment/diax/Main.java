package io.bfnt.comportment.diax;

import com.google.inject.*;
import com.google.inject.name.Names;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.bfnt.comportment.diax.lib.command.CommandHandler;
import io.bfnt.comportment.diax.lib.music.DisconnectListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.json.JSONException;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static com.knockturnmc.api.util.ConfigurationUtils.getDataFolder;
import static com.knockturnmc.api.util.ConfigurationUtils.loadConfiguration;
import static io.bfnt.comportment.diax.util.Utils.log;

/**
 * Created by Comporment on 22/03/2017 at 19:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class Main implements Module, ComponentProvider {
    public static final String VERSION;
    private static JDA[] shards;

    static {
        InputStreamReader reader = new InputStreamReader(Main.class.getResourceAsStream("/version"));
        BufferedReader txtReader = new BufferedReader(reader);
        String version;
        try {
            version = txtReader.readLine();
        } catch (IOException e) {
            version = "null";
            LoggerFactory.getLogger(Main.class).error("Can not find version file for CoreAPI");
        }
        VERSION = version;
    }

    private final Injector injector;
    private DiaxProperties properties;

    public Main() {
        ClassLoader cl = getClass().getClassLoader();
        properties = loadConfiguration(cl, "diax.properties", getDataFolder(), DiaxProperties.class);
        injector = Guice.createInjector(this);
    }

    /**
     * Method fired when the bot is started up.
     *
     * @param args Doesn't matter what they are, no usage.
     * @throws Exception JDABuilder will throw something.
     * @since Azote
     */
    public static void main(String[] args) throws Exception {
        try {
            new Main().main();
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * A method to get an array containing all of the {@link JDA} shard instances.
     *
     * @return An array containing all of the {@link JDA} shards. (Might be null if no shards)
     * @since Azote
     */
    public static JDA[] getShards() {
        return shards;
    }

    /**
     * The main method which is triggered on the startup of the bot.
     *
     * @since Azote
     */
    private void main() {
        log("Loading with version " + VERSION);
        int recommendedShards = getRecommendedShards() + 1;
        log(String.format("Starting with %d shard(s).", recommendedShards));
        init(recommendedShards);
        if (shards == null) System.exit(1);
        List<JDA> jdas = Arrays.asList(shards);
        log("Unique users on startup: " + jdas.stream().flatMap(shard -> shard.getUsers().stream().distinct()).count());
        log("Guilds on startup: " + jdas.stream().flatMap(shard -> shard.getGuilds().stream()).distinct().count());
    }

    /**
     * Method to query Discord to see how many shards the bot should use when making a {@link JDA}
     *
     * @return the amount of shards the bot should use.
     * @since Azote
     */
    private int getRecommendedShards() {
        try {
            HttpResponse<JsonNode> request = Unirest.get("https://discordapp.com/api/gateway/bot")
                    .header("Authorization", "Bot " + properties.getMainToken())
                    .header("Content-Type", "application/json").asJson();
            return Integer.parseInt(request.getBody().getObject().get("shards").toString());
        } catch (UnirestException | JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * Method fired to start a JDA instance, uses sharding if needed.
     *
     * @since Azote
     */
    private void init(int amount) {
        shards = new JDA[amount];
        for (int i = 0; i < amount; i++) {
            JDA jda = null;
            try {
                JDABuilder builder = new JDABuilder(AccountType.BOT)
                        .setToken(properties.getMainToken())
                        .setAudioEnabled(true)
                        .setStatus(OnlineStatus.IDLE)
                        .setGame(Game.of(properties.getCommandPrefix() + "help | Shards: " + amount))
                        .addListener(injector.getInstance(CommandHandler.class), new DisconnectListener());
                if (amount > 1) {
                    jda = builder.useSharding(i, amount).buildBlocking();
                } else {
                    jda = builder.buildBlocking();
                }
            } catch (LoginException | RateLimitedException | InterruptedException exception) {
                exception.printStackTrace();
            }
            if (jda != null) {
                shards[i] = jda;
                log(String.format("Shard %d has been loaded successfully.", i + 1));
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log(String.format("Finished loading with %d shard(s).", amount));
    }

    /**
     * TODO: Get Crystal to document properly [including args and return]
     * @since Brazen
     */
    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(String.class).annotatedWith(Names.named("command.prefix")).toProvider(properties::getCommandPrefix);
        binder.bind(DiaxProperties.class).toProvider(() -> properties);
    }

    /**
     * TODO: Get Crystal to document properly [including args and return]
     * @see Injector#getInstance(Key) You cannot inject an injector, this is a workaround.
     * @since Brazen
     */
    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}