package com.ohgiraffers.section03.sqlinjection;

import java.sql.*;
import java.util.Scanner;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        // 회원 id 와 이름을 입력받고 두개가 일치하는 employee 가 있는지 확인하는 기능

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("회원 ID 와 이름이 일치하는거 확인");
        System.out.print("ID를 입력 : ");
        String empId = sc.nextLine();
        System.out.print("회원 이름 입력 : ");
        String empName = sc.nextLine();

        // 위치 홀더 를 이용해서 empId , empName 값을 모르기 떄문에?
        String query = "select * from employee where emp_id = ? and emp_name = ?";
        System.out.println(query);

        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,empId); // 1, emp id = ?
            pstmt.setString(2,empName); // 2,emp name = ?

            rset = pstmt.executeQuery();

            if (rset.next()){
                System.out.println(rset.getString("emp_name") + " 님 환영합니다. ");
            } else {
                System.out.println(" 회원 정보 없음 ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

    }
}
