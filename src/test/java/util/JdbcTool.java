package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;
/**
 * @author:zhuli
 * @description
 * @date 2019/9/17
 */

import com.mysql.jdbc.Statement;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTool {
    public JdbcTool() {
    }

    public static Connection getConnection(String propertName) throws Exception {
        Properties properties = new Properties();
        InputStream inStream = JdbcTool.class.getClassLoader().getResourceAsStream(propertName);
        properties.load(inStream);
        String user = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String jdbcDriver = properties.getProperty("jdbcDriver");
        Class.forName(jdbcDriver);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void releaseDB(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

    }
}
