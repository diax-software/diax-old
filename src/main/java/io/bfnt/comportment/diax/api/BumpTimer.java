package io.bfnt.comportment.diax.api;

import net.dv8tion.jda.core.entities.Guild;

import java.util.HashMap;

/**
 * Created by Comporment on 25/03/2017 at 18:49
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class BumpTimer
{
    private static HashMap<Guild, Long> bumps = new HashMap<>();
    public static void addBump(Guild g, Long l)
    {
        if (bumps.containsKey(g)) bumps.remove(g);
        bumps.put(g, l);
    }
    public static void removeBump(Guild g)
    {
        bumps.remove(g);
    }
    public static HashMap<Guild, Long> getBumps()
    {
        return bumps;
    }
}