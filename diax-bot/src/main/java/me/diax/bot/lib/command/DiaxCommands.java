package me.diax.bot.lib.command;

import me.diax.bot.ComponentProvider;
import me.diax.bot.DiaxProperties;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

/**
 * Created by Comporment on 04/04/2017 at 22:40
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@Singleton
public class DiaxCommands {

    private static final String COMMAND_PACKAGE = "me.diax.bot.commands";
    private final ComponentProvider provider;
    private final DiaxProperties properties;
    private final Map<DiaxCommandDescription, Class<? extends DiaxCommand>> commands;

    @Inject
    public DiaxCommands(ComponentProvider provider, DiaxProperties properties) {
        this.provider = provider;
        this.properties = properties;
        this.commands = new HashMap<>();

        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        Reflections reflections = new Reflections(COMMAND_PACKAGE);
        List<String> ignoredCommands = Arrays.asList(properties.getIgnoredCommands().trim().split(","));
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(DiaxCommandDescription.class);
        for (Class<?> type : types) {
            DiaxCommandDescription description = type.getAnnotation(DiaxCommandDescription.class);
            if (Collections.disjoint(Arrays.asList(description.triggers()), ignoredCommands)) {
                commands.put(description, (Class<? extends DiaxCommand>) type);
            }
        }
    }

    public Set<DiaxCommandDescription> getCommands() {
        return commands.keySet();
    }

    public DiaxCommandDescription find(String input) {
        for (DiaxCommandDescription cmd : commands.keySet()) {
            for (String s : cmd.triggers()) {
                if (input.equalsIgnoreCase(s)) {
                    return cmd;
                }
            }
        }
        return null;
    }

    public DiaxCommand newInstance(DiaxCommandDescription description) {
        Class<? extends DiaxCommand> type;
        if (description != null && (type = commands.get(description)) != null) {
            return provider.getInstance(type);
        } else {
            return null;
        }
    }
}