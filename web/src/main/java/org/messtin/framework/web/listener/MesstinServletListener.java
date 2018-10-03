package org.messtin.framework.web.listener;

import org.messtin.framework.core.Core;
import org.messtin.framework.core.Init;
import org.messtin.framework.core.config.Constants;
import org.messtin.framework.web.loader.WebAppLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * When tomcat init the whole project, will run this class firstly.
 *
 * @author majinliang
 */
public class MesstinServletListener implements ServletContextListener {
    private static final String SCAN_PACKET = "scanPacket";

    /**
     * Do two things.
     * 1. add {@link WebAppLoader} into loader map.
     * 2. get packages we need scan.
     *
     * @param event the init event.
     */
    public void contextInitialized(ServletContextEvent event) {

        Init.addLoader(WebAppLoader.class);

        String scanPacket = event.getServletContext().getInitParameter(SCAN_PACKET);
        String[] packages = scanPacket.split(Constants.COMMA);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = packages[i].trim();
        }

        try {
            Core.init(packages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("MesstinServletListener#contextDestroyed is called.");
    }
}
