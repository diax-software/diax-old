package me.diax.bot;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.knockturnmc.api.util.ConfigurationUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import me.diax.bot.lib.audio.DiaxDisconnectListener;
import me.diax.bot.lib.command.DiaxCommandHandler;
import me.diax.bot.lib.util.DiaxLogger;
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

/**
 * Created by Comporment on 04/04/2017 at 19:45
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class DiaxBot implements ComponentProvider, Module {

    public static final String VERSION;
    public static JDA[] SHARDS;

    static {
        InputStreamReader reader = new InputStreamReader(DiaxBot.class.getResourceAsStream("/version"));
        BufferedReader txtReader = new BufferedReader(reader);
        String version;
        try {
            version = txtReader.readLine();
        } catch (IOException e) {
            version = "Unknown";
            LoggerFactory.getLogger(DiaxBot.class).error("Could not find the build of diax-bot.");
        }
        VERSION = version;
    }

    private final Injector injector;
    private final DiaxProperties properties;

    private DiaxBot() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        injector = Guice.createInjector(this);
        properties = ConfigurationUtils.loadConfiguration(classLoader, "diax.properties", ConfigurationUtils.getDataFolder(), DiaxProperties.class);
    }

    public static void main(String[] args) {
        new DiaxLogger();
        new DiaxBot().main();
    }

    private void main() {
        initialise(getShardAmount());
    }

    private void initialise(int shards) {
        DiaxBot.SHARDS = new JDA[shards];
        for (int i = 0; i < shards; i++) {
            JDA jda = null;
            try {
                JDABuilder builder = new JDABuilder(AccountType.BOT)
                        .addEventListener(injector.getInstance(DiaxCommandHandler.class), new DiaxDisconnectListener())
                        .setAudioEnabled(true)
                        .setGame(Game.of(properties.getGame()))
                        .setToken(properties.getToken())
                        .setStatus(OnlineStatus.ONLINE);
                if (shards > 1) {
                    jda = builder.useSharding(i, shards).buildBlocking();
                } else {
                    jda = builder.buildBlocking();
                }
            } catch (LoginException | RateLimitedException | InterruptedException ignored) {
            }
            if (jda != null) {
                DiaxBot.SHARDS[i] = jda;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private int getShardAmount() {
        try {
            HttpResponse<JsonNode> request = Unirest.get("https://discordapp.com/api/gateway/bot")
                    .header("Authorization", "Bot " + properties.getToken())
                    .header("Content-Type", "application/json").asJson();
            return Integer.parseInt(request.getBody().getObject().get("shards").toString()) + 1;
        } catch (UnirestException | JSONException | NumberFormatException | NullPointerException exception) {
            exception.printStackTrace();
        }
        return 2;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        /* binder.bind(String.class)
                .annotatedWith(Names.named("diax.commands.prefix"))
                .toProvider(properties::getPrefix); */
        binder.bind(DiaxProperties.class).toProvider(() -> properties);
    }

    @Override
    public <E> E getInstance(Class<E> type) {
        return injector.getInstance(type);
    }
}