package com.KesifPlus.database;

import com.KesifPlus.utility.ConfigurationReader;

import java.sql.*;

public class DatabaseUtilities {


    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    public static ResultSet resultSet;
    /**
     * method database connection i olusturmak icin kullanildi
     *
     * @author omeryttnc
     * @since 10.02.2024
     */
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(
                    ConfigurationReader.getProperty("urlDb"),
                    ConfigurationReader.getProperty("usernameDb"),
                    ConfigurationReader.getProperty("passwordDb")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method connectionlarin kapatilmasi icin kullanildi
     * kapatilacak connection statement resultSet
     *
     * @author omeryttnc
     * @since 10.02.2024
     */
    public static void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void updateQueryStatement(String sql) { // insert update delete
        try {
            statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method son eklenen urunu approve etmek icin kullanildi
     *
     * @author omeryttnc
     * @since 13.02.2024
     */
    public static void approveLastProduct() {
        updateQueryStatement("UPDATE `hub_product` SET `product_listing_state` = 'APPROVED' WHERE `product_listing_state` LIKE 'IN_REVIEW' order BY id DESC limit 1;");

    }
}
// database url https://phpmyadmin-test.urbanicfarm.com/index.php?route=/&route=%2F


