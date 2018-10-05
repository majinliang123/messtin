package org.messtin.framework.jdbc.util;

import org.messtin.framework.core.config.Constants;
import org.messtin.framework.jdbc.config.Config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * The util for properties.
 *
 * @author majinliang
 */
public final class PropertiesUtil {
    private static final String PROPERTIES = "application.properties";

    public static void readPropertiesToConfig() throws IOException, IllegalAccessException {

        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));

        for (Field field : Config.class.getDeclaredFields()) {
            String key = field.getName().replace(Constants.UNDERSCORE, Constants.DOT);
            field.set(null, properties.get(key));
        }
    }
}
