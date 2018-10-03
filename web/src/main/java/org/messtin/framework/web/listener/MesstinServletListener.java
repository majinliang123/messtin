package org.messtin.framework.web.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.Core;
import org.messtin.framework.core.Init;
import org.messtin.framework.core.config.Constants;
import org.messtin.framework.web.loader.WebAppLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;

/**
 * When tomcat init the whole project, will run this class firstly.
 *
 * @author majinliang
 */
public class MesstinServletListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(MesstinServletListener.class);
    private static final String SCAN_PACKET = "scanPacket";

    /**
     * Do three things.
     * 1. add {@link WebAppLoader} into loader map.
     * 2. get packages we need scan.
     * 3. init the whole project.
     *
     * @param event the init event.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("Start initialize servlet context.");

        Init.addLoader(WebAppLoader.class);

        String scanPacket = event.getServletContext().getInitParameter(SCAN_PACKET);
        String[] packages = scanPacket.split(Constants.COMMA);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = packages[i].trim();
        }

        try {
            logger.info("Will scan package: ", Arrays.toString(packages));
            Core.init(packages);
            logger.info("Success initialized servlet context.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("MesstinServletListener#contextDestroyed is called.");
    }
}
