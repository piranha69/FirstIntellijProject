package cz.tomanjir.service;

/**
 * Provides generic support for Spring's lifecycle callbacks.
 * <p>
 * Note: Method names are strictly tied with default-init-method and default-destroy-method attributes in config/spring/services.xml.
 */
interface Service {
    /**
     * Initializes this {@link Service}.
     */
    void initService();

    /**
     * Destroys this {@link Service}.
     */
    void destroyService();
}