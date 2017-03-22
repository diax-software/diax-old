package io.bfnt.comportment.diax;

import io.bfnt.comportment.diax.token.Token;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by Comporment on 22/03/2017 at 19:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public class Main
{
    /**
     * The main method is called when the application starts up.
     * Attempts to make an {@link net.dv8tion.jda.core.JDA} object
     * using the {@link net.dv8tion.jda.core.JDABuilder} (Needed for everything else.)
     * @param args {@link java.lang.String} {"foo", "bar", "baz"}
     */
    public static void main(String[] args)
    {
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(Token.main())
                    .buildBlocking();
        }
        catch (LoginException|InterruptedException|RateLimitedException exception)
        {
            System.err.println("Someone thing wrong :/\nHave an error message so I can make it up to you <3\n\n");
            exception.printStackTrace();
            System.err.println("\n\nEnd of error message. Hope you fix the bug.");
        }
    }
}