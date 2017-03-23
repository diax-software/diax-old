package io.bfnt.comportment.diax.api.command;

import io.bfnt.comportment.diax.api.Diax;

/**
 * Created by Comporment on 23/03/2017 at 16:44
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class DiaxCommand extends Diax implements Command, Comparable<DiaxCommand>
{
    public int compareTo(DiaxCommand o)
    {
        return o.getClass().getAnnotation(CommandDescription.class).name().compareTo(getClass().getAnnotation(CommandDescription.class).name());
    }
}