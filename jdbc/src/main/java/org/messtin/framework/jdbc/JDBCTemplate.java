package org.messtin.framework.jdbc;

import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.annotation.PostConstruct;
import org.messtin.framework.core.util.StringUtil;
import org.messtin.framework.jdbc.model.BaseModel;
import org.messtin.framework.jdbc.util.DBUtil;
import org.messtin.framework.jdbc.util.PropertiesUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Bean("JDBCTemplate")
public class JDBCTemplate {
    private Connection connection;

    @PostConstruct
    public void init() throws IOException, IllegalAccessException {
        PropertiesUtil.readPropertiesToConfig();
        connection = DBUtil.connect();
    }

    public ResultSet query(String sql, Object... params) {

        ResultSet resultSet = null;
        sql = buildSql(sql, params);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public <T> List<T> query(Class<T> clazz, String sql, Object... params) {

        if (!BaseModel.class.isAssignableFrom(clazz)) {
            return null;
        }
        List<T> results = new ArrayList<>();
        sql = buildSql(sql, params);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T result = clazz.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(result, resultSet.getObject(field.getName()));
                }
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return results;
    }

    public int update(String sql, Object... params) {
        int updateRows = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            updateRows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateRows;
    }

    private String buildSql(String sql, Object... params) {
        sql += " ";
        String[] sqlRanks = sql.split("\\?");
        if (sqlRanks.length == 1) {
            return sql;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sqlRanks.length; i++) {
            sb.append(sqlRanks[i]);
            if (i != sqlRanks.length - 1) {
                try {
                    Object value = params[i];
                    if (!StringUtil.isNullOrEmpty(value)) {
                        if (Date.class.isAssignableFrom(value.getClass())) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            value = dateFormat.format((Date) value);
                        }
                        if (String.class.isAssignableFrom(value.getClass())) {
                            value = "'" + value + "'";
                        }
                    }
                    sb.append(value);
                } catch (Exception e) {
                }
            }
        }
        return sb.toString();
    }

}
