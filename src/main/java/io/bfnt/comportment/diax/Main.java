package io.bfnt.comportment.diax;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import io.bfnt.comportment.diax.api.Diax;
import io.bfnt.comportment.diax.api.command.CommandHandler;
import io.bfnt.comportment.diax.api.music.DisconnectListener;
import io.bfnt.comportment.diax.api.music.GuildMusicManager;
import io.bfnt.comportment.diax.token.Token;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.HashMap;
import java.util.Map;

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
                    .addListener(new CommandHandler(), new DisconnectListener())
                    .setAudioEnabled(true)
                    .setGame(Game.of("<>help", "https://twitch.tv/skiletro"))
                    .buildBlocking();
        }
        catch (Exception exception)
        {
            System.err.println("Someone thing wrong :/\nHave an error message so I can make it up to you <3\n");
            exception.printStackTrace();
            System.err.println("\nEnd of error message. Hope you fix the bug.");
        }
    }
}