package io.bfnt.comportment.diax.lib.command;

import io.bfnt.comportment.diax.ComponentProvider;
import io.bfnt.comportment.diax.DiaxProperties;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

/**
 * Created by Comporment on 30/03/2017 at 12:06
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@Singleton
public class Commands {

    private static final String COMMAND_PACKAGE = "io.bfnt.comportment.diax.commands";
    private final ComponentProvider provider;
    private final DiaxProperties properties;
    private final Map<CommandDescription, Class<? extends DiaxCommand>> commands;

    @Inject
    public Commands(ComponentProvider provider, DiaxProperties properties) {
        this.provider = provider;
        this.properties = properties;
        this.commands = new HashMap<>();

        init();
    }

    /**
     * Method called when a nice instance of the {@link Commands} class is created.
     * TODO: Get Crystal to document
     * @since Brazen
     */
    @SuppressWarnings("unchecked")
    private void init() {
        Reflections reflections = new Reflections(COMMAND_PACKAGE);
        List<String> ignoredCommands = Arrays.asList(properties.getIgnoredCommands().trim().split(","));
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(CommandDescription.class);
        for (Class<?> type : types) {
            CommandDescription description = type.getAnnotation(CommandDescription.class);
            if (Collections.disjoint(Arrays.asList(description.triggers()), ignoredCommands)) {
                commands.put(description, (Class<? extends DiaxCommand>) type);
            }
        }
    }

    /**
     * A method to get all of the registered commands.
     *
     * @return All of the commands registered {@link #commands}
     * @since Azote
     */
    public Set<CommandDescription> getCommands() {
        return commands.keySet();
    }

    /**
     * A method to find the {@link DiaxCommand} that might be executed.
     *
     * @param input A string containing the truncated input of the message.
     * @return The {@link CommandDescription} of the {@link DiaxCommand}
     * @since Brazen
     */
    public CommandDescription find(String input) {
        for (CommandDescription cmd : commands.keySet()) {
            for (String s : cmd.triggers()) {
                if (input.startsWith(s)) {
                    return cmd;
                }
            }
        }
        return null;
    }

    /**
     * A method that attempts to create a new instance of the {@link DiaxCommand} associated with the {@link CommandDescription} if the description is properly formatted.
     *
     * @param description The {@link CommandDescription} of the class.
     * @return The {@link DiaxCommand} that was created.
     * @since Brazen
     */
    public DiaxCommand newInstance(CommandDescription description) {
        Class<? extends DiaxCommand> type;
        if (description != null && (type = commands.get(description)) != null) {
            return provider.getInstance(type);
        } else {
            return null;
        }
    }
}