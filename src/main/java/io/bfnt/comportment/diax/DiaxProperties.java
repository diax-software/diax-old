package io.bfnt.comportment.diax;

import com.knockturnmc.api.util.NamedProperties;
import com.knockturnmc.api.util.Property;

public class DiaxProperties extends NamedProperties {

    @Property(value = "diax.token.main")
    private String mainToken;
    @Property(value = "diax.token.felisCatV1")
    private String felisCatV1;
    @Property(value = "diax.token.felisCatV2")
    private String felisCatV2;
    @Property(value = "diax.commands.ignored")
    private String ignoredCommands;
    @Property(value = "diax.commands.prefix", defaultvalue = "<<")
    private String commandPrefix;

    /**
     * Method that gets the main account token for Diax to use to login as the bot.
     *
     * @return A string containing the main token as defined in the {@code diax.properties} file.
     * @since Brazen
     */
    public String getMainToken() {
        return mainToken;
    }

    /**
     * Method that gets the felis cat v1 token for use in the felis.cat/api/v1 endpoints.
     *
     * @return A string containing the felis cat v1 token as defined in the {@code diax.properties} file.
     * @since Brazen
     */
    public String getFelisCatV1() {
        return felisCatV1;
    }

    /**
     * Method that gets the felis cat v2 token for use in the felis.cat/api/v2 endpoints.
     *
     * @return A string containing the felis cat v2 token as defined in the {@code diax.properties} file.
     * @since Brazen
     */
    public String getFelisCatV2() {
        return felisCatV2;
    }

    /**
     * Method that gets the ignored/unused commands.
     *
     * @return A string containing a comma separated list of the unused commands as defined in the {@code diax.properties} file.
     * @since Brazen
     */
    public String getIgnoredCommands() {
        return ignoredCommands;
    }

    /**
     * Method that gets the command prefix that tells the {@link io.bfnt.comportment.diax.lib.command.CommandHandler} that the {@link io.bfnt.comportment.diax.lib.command.DiaxCommand} can be executed.
     *
     * @return A string containing the command prefix as described in the {@code diax.properties} file.
     * @since Brazen
     */
    public String getCommandPrefix() {
        return commandPrefix;
    }
}