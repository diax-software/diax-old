package me.diax.bot;

import com.knockturnmc.api.util.NamedProperties;
import com.knockturnmc.api.util.Property;

/**
 * Created by Comporment on 04/04/2017 at 20:02
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class DiaxProperties extends NamedProperties {

    @Property(value = "diax.tokens.account", defaultvalue = "Mjk1MTYxNTUyNzk1OTkyMDY0.C8bMYw.RLo96PDsJGsBgWk7OgErRYOwKQw")
    private String token;
    @Property(value = "diax.commands.prefix", defaultvalue = "<<")
    private String prefix;
    @Property(value = "diax.commands.ignored")
    private String ignoredCommands;
    @Property(value = "diax.game", defaultvalue = "eeeeeeeeeeeeeeeee")
    private String game;

    public String getToken() {
        return token;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getGame() {
        return game;
    }

    public String getIgnoredCommands() {
        return ignoredCommands;
    }
}