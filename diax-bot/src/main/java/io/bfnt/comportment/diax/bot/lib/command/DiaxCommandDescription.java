package io.bfnt.comportment.diax.bot.lib.command;

import net.dv8tion.jda.core.Permission;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Comporment on 04/04/2017 at 22:22
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DiaxCommandDescription {

    boolean guildOnly() default false;

    boolean donorOnly() default false;

    boolean ownerOnly() default false;

    String[] triggers() default {};

    String description() default "";

    Permission permission() default Permission.MESSAGE_WRITE;

    int minimumArgs() default 0;
}