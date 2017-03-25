package io.bfnt.comportment.diax.api.command;

import net.dv8tion.jda.core.Permission;

import java.lang.annotation.*;

/**
 * Created by Comporment on 23/03/2017 at 21:51
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDescription
{
    String description() default "";
    String emoji() default "";
    String name() default "";
    String args() default "";
    Permission permission() default Permission.MESSAGE_WRITE;
    boolean guildOnly() default false;
    int minimumArgs() default 0;
}