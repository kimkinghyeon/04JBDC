package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class application2 {
    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String user = "root";
        String password = "ohgiraffers";

        // DB 접속을 위한 커넥션 객체
        // 생성하기 위해 레퍼런스 변수 선언
        // 데이터베이스 종류, 계정 정보
        Connection con = null;

        // 사용할 드라이버 등록
        try {
            Class.forName(driver);

            con = DriverManager.getConnection
                    (url, user, password);
            //  연결객체 생성되었는지 확인 (객체의 주소값이 반환되는것 확인)
            System.out.println("con = " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 자원 반납
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
