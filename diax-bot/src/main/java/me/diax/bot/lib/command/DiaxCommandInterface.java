package me.diax.bot.lib.command;

import net.dv8tion.jda.core.entities.Message;

/**
 * Created by Comporment on 04/04/2017 at 22:21
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
@FunctionalInterface
public interface DiaxCommandInterface {

    void execute(Message trigger, String args);
}