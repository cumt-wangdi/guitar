package com.example.guitar.dao;

import java.sql.*;

public class SqliteUtil {
    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:C:/Desktop/guitar.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet rs, Statement statement, Connection connection) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Connection conn = SqliteUtil.getConnection();
        String sql = "CREATE TABLE guitar (\n" +
                "\tid INTEGER PRIMARY KEY autoincrement,\n" +
                "\tname VARCHAR(100),\n" +
                "\tprice DOUBLE,\n" +
                "\tbuilder VARCHAR(100),\n" +
                "\tmodel VARCHAR(100),\n" +
                "\ttype VARCHAR(100),\n" +
                "\tback_wood VARCHAR(100),\n" +
                "\ttop_wood VARCHAR(100)\n" +
                ")";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
