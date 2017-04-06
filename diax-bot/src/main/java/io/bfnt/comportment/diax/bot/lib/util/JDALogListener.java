package io.bfnt.comportment.diax.bot.lib.util;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.utils.SimpleLog;
import net.dv8tion.jda.core.utils.SimpleLog.Level;
import net.dv8tion.jda.core.utils.SimpleLog.LogListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by NachtRaben on 4/5/2017.
 */
public class JDALogListener implements LogListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDA.class);

    public JDALogListener() {
        SimpleLog.addListener(this);
        SimpleLog.LEVEL = Level.OFF;
    }

    @Override
    public void onLog(SimpleLog simpleLog, Level level, Object o) {
        switch (level) {
            case TRACE:
                LOGGER.trace(o.toString());
                break;
            case DEBUG:
                LOGGER.debug(o.toString());
                break;
            case INFO:
                LOGGER.info(o.toString());
                break;
            case WARNING:
                LOGGER.warn(o.toString());
                break;
            case FATAL:
                LOGGER.error(o.toString());
                break;
        }
    }

    @Override
    public void onError(SimpleLog simpleLog, Throwable throwable) {
        LOGGER.error(throwable.getMessage(), throwable);
    }
}