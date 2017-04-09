package me.diax.bot;

/**
 * Created by Comporment on 04/04/2017 at 19:57
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public interface ComponentProvider {
    <T> T getInstance(Class<T> type);
}