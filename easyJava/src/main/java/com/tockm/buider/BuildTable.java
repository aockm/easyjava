package com.tockm.buider;

import com.tockm.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BuildTable {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection connection = null;

    private static String  SQL_SHOW_TABLES_STATUS = "show table status";

    static {
        String driver = PropertiesUtils.getProperty("db.driver.name");
        String url = PropertiesUtils.getProperty("db.url");
        String user = PropertiesUtils.getProperty("db.username");
        String password = PropertiesUtils.getProperty("db.password");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            logger.error("数据库连接失败", e);
        }
    }
    public static void getTables() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SHOW_TABLES_STATUS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tableName = resultSet.getString("name");
                String comment = resultSet.getString("comment");
                logger.info("tableName:{}, comment:{}", tableName, comment);
            }
        }catch (Exception e) {
            logger.error("读取表失败", e);
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
