package com.ohgiraffers.config;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {

    // 커넥션을 리턴해주는 메소드

    public static Connection getConnection() {

        Connection con = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/connection/connection-info.properties"));

            // key-value
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            // 사용할 드라이버 등록
            Class.forName(driver);

            // 드라이버매니저 를 통해서 커넥션 객체 생성
//        con = DriverManager.getConnection
//                (url, user, password);
            con = DriverManager.getConnection
                    (url, prop);

            //  연결객체 생성되었는지 확인 (객체의 주소값이 반환되는것 확인)

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con;

    }

    public static void close(Connection con) {
        try {
            if (con != null && con.isClosed()) {

                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
// 오버로딩
    public static void close(Statement stmt) {
        try {
            if (stmt != null && stmt.isClosed()) {

                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset) {
        try {
            if (rset != null && rset.isClosed()) {

                rset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
