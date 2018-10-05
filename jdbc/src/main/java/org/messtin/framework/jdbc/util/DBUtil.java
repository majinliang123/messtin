package org.messtin.framework.jdbc.util;

import org.messtin.framework.jdbc.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The util for db.
 *
 * @author majinliang
 */
public final class DBUtil {

    public static Connection connect() {

        Connection conn = null;
        try {
            Class.forName(Config.datasource_driver);
            conn = DriverManager.getConnection(Config.datasource_url, Config.datasource_username, Config.datasource_password);
            System.out.println("connect database success.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;

    }
}
