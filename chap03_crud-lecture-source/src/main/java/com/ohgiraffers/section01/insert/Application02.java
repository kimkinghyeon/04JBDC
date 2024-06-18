package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args)  {


        Connection con =getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {

            prop.loadFromXML(new FileInputStream
                    ("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertMenu");

            Scanner sc = new Scanner(System.in);
            System.out.print(" 메뉴의 이름을 입력하세요 ");
            String menuName = sc.nextLine();
            System.out.print(" 메뉴의 가격을 입력하세요 ");
            int menuPrice = sc.nextInt();
            System.out.print(" 메뉴의 카테고리를 입력하세요 ");
            int menuCategory = sc.nextInt();
            System.out.print(" 메뉴의 주문가능 여부 입력하세요 ");
            sc.nextLine();
            String menudrder = sc.nextLine().toUpperCase();



            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuName);
            pstmt.setInt(2,menuPrice);
            pstmt.setInt(3,menuCategory);
            pstmt.setString(4,menudrder);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0){
            System.out.println("메뉴 저장 성공!!!");
        } else {
            System.out.println("메뉴 저장 실패!!!");
        }

    }
}
