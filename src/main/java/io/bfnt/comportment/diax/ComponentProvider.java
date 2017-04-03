package io.bfnt.comportment.diax;

/**
 * Represents a component provider to obtain instances of module classes
 */
public interface ComponentProvider {
    /**
     * TODO: Get Crystal to document properly [including args and return]
     * @since Brazen
     */
    <T> T getInstance(Class<T> type);
}