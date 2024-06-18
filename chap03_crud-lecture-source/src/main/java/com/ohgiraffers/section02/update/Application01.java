package com.ohgiraffers.section02.update;

import com.ohgiraffers.model.menuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 메뉴 번호를 입력");
        int menuCode = sc.nextInt();
        System.out.println("변경할 메뉴 이름을 입력");
        String menuName = sc.next();
        System.out.println("변경할 메뉴 가격을 입력");
        int menuPrice = sc.nextInt();

//        menuDTO changedMenu = new menuDTO();
//        changedMenu.setMenuCode(menuCode);
//        changedMenu.setMenuName(menuName);
//        changedMenu.setMenuPrice(menuPrice);

        /*------------------------------------------------------------------------------------------------------------*/

        // 쿼리 가져오기

        try {

            menuDTO changedMenu = new menuDTO();

            changedMenu.setMenuCode(menuCode);
            changedMenu.setMenuName(menuName);
            changedMenu.setMenuPrice(menuPrice);

            prop.loadFromXML(new FileInputStream
                    ("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("updateMenu");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, changedMenu.getMenuName());
            pstmt.setInt(2, changedMenu.getMenuPrice());
            pstmt.setInt(3, changedMenu.getMenuCode());

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
