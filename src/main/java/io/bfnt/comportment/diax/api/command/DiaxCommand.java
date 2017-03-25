package io.bfnt.comportment.diax.api.command;

import io.bfnt.comportment.diax.api.Diax;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

/**
 * Created by Comporment on 23/03/2017 at 16:44
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class DiaxCommand extends Diax implements Command, Comparable<DiaxCommand>
{
    public int compareTo(DiaxCommand o)
    {
        return o.getCommandAnnotation().name().compareTo(getClass().getAnnotation(CommandDescription.class).name());
    }
    protected Member getMemberFromString(String s, Guild g)
    {
        return g.getMemberById(s.replaceAll("[<!@>]", ""));
    }
    public CommandDescription getCommandAnnotation()
    {
        return getClass().getAnnotation(CommandDescription.class);
    }
    public String getHelpDescription()
    {
        CommandDescription cd = getCommandAnnotation();
        return String.format("`%s %s%s %s `", cd.emoji(), prefix(), cd.name(), cd.args());
    }
}