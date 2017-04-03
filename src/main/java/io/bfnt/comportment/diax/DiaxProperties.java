package io.bfnt.comportment.diax;

import com.knockturnmc.api.util.NamedProperties;
import com.knockturnmc.api.util.Property;

public class DiaxProperties extends NamedProperties {

    @Property(value = "diax.token.main")
    private String mainToken;
    @Property(value = "diax.token.aprilFools")
    private String felisCatV1;
    @Property(value = "diax.token.felisCatV2")
    private String felisCatV2;
    @Property(value = "diax.commands.ignored", defaultvalue = "play,shuffle,skip")
    private String ignoredCommands;
    @Property(value = "diax.commands.prefix", defaultvalue = "<<")
    private String commandPrefix;

    public String getMainToken() {
        return mainToken;
    }

    public String getFelisCatV1() {
        return felisCatV1;
    }

    public String getFelisCatV2() {
        return felisCatV2;
    }

    public String getIgnoredCommands() {
        return ignoredCommands;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }
}
