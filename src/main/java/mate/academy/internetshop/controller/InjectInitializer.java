package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import mate.academy.internetshop.lib.Injector;
import org.apache.log4j.Logger;

public class InjectInitializer implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(InjectInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            System.out.println("Dependency injection started...");
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            LOGGER.error("Dependencies wasn't initialized : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
