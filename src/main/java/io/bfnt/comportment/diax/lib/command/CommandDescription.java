package io.bfnt.comportment.diax.lib.command;

import net.dv8tion.jda.core.Permission;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Comporment on 28/03/2017 at 14:56
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CommandDescription
{
    /**
     * A boolean to describe if the {@link DiaxCommand} is only allowed to be used in a {@link net.dv8tion.jda.core.entities.Guild}
     *
     * @return If the {@link DiaxCommand} is only allowed to be used in a {@link net.dv8tion.jda.core.entities.Guild}
     * @since Azote
     */
    boolean guildOnly() default true;

    /**
     * An array of Strings which describe what will trigger a {@link DiaxCommand}
     *
     * @return never null an array of Strings (i.e) {"ping", "pong", "peng"}
     * @since Azote
     */
    String[] triggers() default {};

    /**
     * A string containing the description of the {@link DiaxCommand}
     *
     * @return The description of the {@link DiaxCommand}
     * @since Azote
     */
    String description() default "_____";

    /**
     * A {@link Permission} which the {@link net.dv8tion.jda.core.entities.Member} must have to use the {@link DiaxCommand}
     *
     * @return The {@link Permission} needed to use the {@link DiaxCommand} in a {@link net.dv8tion.jda.core.entities.Guild}
     * @since Azote
     */
    Permission permission() default Permission.MESSAGE_WRITE;

    /**
     * The minimum args that a {@link DiaxCommand} must have to be executed.
     *
     * @return Tye amount of minimum args the {@link DiaxCommand} must have.
     * @since Azote
     */
    int minimumArgs() default 0;
}
