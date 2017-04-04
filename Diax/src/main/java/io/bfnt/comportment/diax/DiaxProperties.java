package io.bfnt.comportment.diax;

import com.knockturnmc.api.util.NamedProperties;
import com.knockturnmc.api.util.Property;

/**
 * Created by Comporment on 04/04/2017 at 20:02
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class DiaxProperties extends NamedProperties {

    @Property(value = "diax.token", defaultvalue = "Get your own.")
    private String token;
    @Property(value = "diax.prefix", defaultvalue = "<<")
    private String prefix;
    @Property(value = "diax.commands.ignored")
    private String ignoredCommands;

    public String getToken() {
        return token;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getIgnoredCommands(){
        return ignoredCommands;
    }
}
