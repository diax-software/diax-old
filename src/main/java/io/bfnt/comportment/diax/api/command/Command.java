package io.bfnt.comportment.diax.api.command;

import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 23/03/2017 at 16:53
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public interface Command
{
    void execute(Message message);
}