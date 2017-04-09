package me.diax.bot.lib.util;

/*
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import me.diax.bot.DiaxBot;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import net.dv8tion.jda.core.utils.logging.ShardMarker;
*/

import net.dv8tion.jda.core.utils.SimpleLog;
import net.dv8tion.jda.core.utils.SimpleLog.LogListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by Comportment on 09/04/2017.
 * If you don't understand this, we are screwed.
 */
public class DiaxLogger /* extends Filter<ILoggingEvent> */ implements LogListener {

    public DiaxLogger() {
        SimpleLog.addListener(this);
        SimpleLog.LEVEL = SimpleLog.Level.OFF;
    }


    /*
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!event.getMarker().contains(new ShardMarker(DiaxBot.SHARDS[0].getShardInfo()))) {
            event.getMarker().add(((JDAImpl) DiaxBot.SHARDS[0]).getShardMarker());
        }
        return FilterReply.ACCEPT;
    }
    */

    @Override
    public void onLog(SimpleLog log, SimpleLog.Level level, Object object) {
        Logger logger = getLoggerFromName(log.name);
        if (object instanceof Throwable) {
            onError(log, (Throwable) object);
            return;
        }
        String message = object.toString();
        switch (level) {
            case INFO: {
                logger.info(message);
                break;
            }
            case WARNING: {
                logger.warn(message);
                break;
            }
            case FATAL: {
                logger.error(message);
                break;
            }
            case DEBUG: {
                logger.debug(message);
                break;
            }
            case TRACE: {
                logger.trace(message);
                break;
            }
        }
    }

    @Override
    public void onError(SimpleLog log, Throwable throwable) {
        Logger logger = getLoggerFromName(log.name);
        logger.error("Failure ", throwable);
    }

    private Logger getLoggerFromName(String name) {
        return LoggerFactory.getLogger(name);
    }
}