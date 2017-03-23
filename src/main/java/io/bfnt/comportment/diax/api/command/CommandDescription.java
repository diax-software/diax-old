package io.bfnt.comportment.diax.api.command;

/**
 * Created by Comporment on 23/03/2017 at 17:58
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface CommandDescription
{
    String name() default "";
    String description() default "";
    String emoji() default "";
    String args() default " ";
}