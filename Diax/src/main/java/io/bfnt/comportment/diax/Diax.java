package io.bfnt.comportment.diax;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.knockturnmc.api.util.ConfigurationUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Comporment on 04/04/2017 at 19:45
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class Diax implements ComponentProvider, Module {

    private final Injector injector;
    private final DiaxProperties properties;
    public static final String VERSION;
    public static JDA[] SHARDS;

    static {
        InputStreamReader reader = new InputStreamReader(Diax.class.getResourceAsStream("/version"));
        BufferedReader txtReader = new BufferedReader(reader);
        String version;
        try {
            version = txtReader.readLine();
        } catch (IOException e) {
            version = "Unknown";
            LoggerFactory.getLogger(Diax.class).error("Could not find the build of Diax.");
        }
        VERSION = version;
    }

    private Diax() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        injector = Guice.createInjector(this);
        properties = ConfigurationUtils.loadConfiguration(classLoader, "diax.properties", ConfigurationUtils.getDataFolder(), DiaxProperties.class);
    }

    public static void main(String[] args) {
        Diax.main(new String[1]);
        try {
            new Diax().main();
        } catch (InterruptedException | RateLimitedException | LoginException | NullPointerException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    private void main() throws InterruptedException, LoginException, RateLimitedException {
        JDA jda = new JDABuilder(AccountType.BOT).setToken(properties.getToken()).buildBlocking();
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(DiaxProperties.class).toProvider(() -> properties);
        binder.bind(String.class).annotatedWith(Names.named("command.prefix")).toProvider(properties::getPrefix);
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}
