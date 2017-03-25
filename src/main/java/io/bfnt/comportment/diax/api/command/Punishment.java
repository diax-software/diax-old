package io.bfnt.comportment.diax.api.command;

/**
 * Created by Comporment on 25/03/2017 at 09:33
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public enum Punishment
{
    BAN("BANNED"),
    KICK("KICKED"),
    SOFT_BAN("SOFT-BANNED"),
    PURGE("PURGED");

    private String past;

    Punishment(String past)
    {
        this.past = past;
    }

    String getPast()
    {
        return past;
    }
}