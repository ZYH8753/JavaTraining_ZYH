package com.zyh.javatraining.weekfive.mustthree;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCTest {
    private static String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
    private static String user = "root";
    private static String password = "123456";

    private static HikariDataSource sHikariDataSource = null;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setMaximumPoolSize(10);
        sHikariDataSource = new HikariDataSource(config);
    }

    public static void main(String[] args) {
//        List<Person> list = query();
        List<Person> list = queryByUsingHikari();
        for (Person person:list) {
            System.out.println(person);
        }

//        updateByTransaction();
    }

    public static List<Person> query() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Person> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from person";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Person person = new Person(rs.getInt("id"), rs.getString("name"));
                list.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void updateByTransaction() {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            ps1 = conn.prepareStatement("update person set name = ? where id = ?");
            ps1.setString(1, "update");
            ps1.setInt(2, 1);

            ps2 = conn.prepareStatement("insert person(name) values (?)");
            ps2.setString(1,"cc");

            ps1.executeUpdate();
            ps2.executeUpdate();

            int a = 1/0;
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps1.close();
                ps2.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Person> queryByUsingHikari() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Person> list = new ArrayList<>();
        try {
            conn = getConnect();
            String sql = "select * from person";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Person person = new Person(rs.getInt("id"), rs.getString("name"));
                list.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static Connection getConnect() throws SQLException {
        return sHikariDataSource.getConnection();
    }
}
