package io.bfnt.comportment.diax;

import io.bfnt.comportment.diax.api.Diax;
import io.bfnt.comportment.diax.api.command.CommandHandler;
import io.bfnt.comportment.diax.api.music.DisconnectListener;
import io.bfnt.comportment.diax.token.Token;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

/**
 * Created by Comporment on 22/03/2017 at 19:09
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public final class Main extends Diax
{
    public static void main(String[] args)
    {
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(Token.main())
                    .setAudioEnabled(true)
                    .setGame(Game.of("<>help"))
                    .buildBlocking();
            jda.addEventListener(new CommandHandler(), new DisconnectListener());
        }
        catch (Exception exception)
        {
            System.err.println("Someone thing wrong :/\nHave an error message so I can make it up to you <3\n");
            exception.printStackTrace();
            System.err.println("\nEnd of error message. Hope you fix the bug.");
        }
    }
}