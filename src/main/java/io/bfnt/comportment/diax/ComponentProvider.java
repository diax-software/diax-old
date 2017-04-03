package io.bfnt.comportment.diax;

/**
 * Represents a component provider to obtain instances of module classes
 */
public interface ComponentProvider {
    <T> T getInstance(Class<T> type);
}
