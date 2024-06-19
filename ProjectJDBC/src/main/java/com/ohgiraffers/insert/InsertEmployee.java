package com.ohgiraffers.insert;



import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class InsertEmployee {
    public void insertEmp() {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {

            prop.loadFromXML(new FileInputStream
                    ("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertEmployee");

            Scanner sc = new Scanner(System.in);
            System.out.print("등록할 사원 번호 : ");
            String epmId = sc.next();
            System.out.print("사원 이름 : ");
            String empName = sc.next();
            System.out.print("사원주민번호 : ");
            String empNo = sc.next();
            System.out.print("잡 코드 : ");
            String job_code = sc.next();
            System.out.print("sal_level : ");
            sc.nextLine();
            String sal_level = sc.nextLine().toUpperCase();

            EmployeeDTO newmenu = new EmployeeDTO();
            newmenu.setEmpId(epmId);
            newmenu.setEmpName(empName);
            newmenu.setEmpNO(empNo);
            newmenu.setJobCode(job_code);
            newmenu.setSalLevel(sal_level);

//            emp_id, emp_name, emp_no, job_code, sal_level,

            /*--------------------------------------------------------------------------------------------------------*/

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, newmenu.getEmpId());
            pstmt.setString(2, newmenu.getEmpName());
            pstmt.setString(3, newmenu.getEmpNO());
            pstmt.setString(4, newmenu.getJobCode());
            pstmt.setString(5, newmenu.getSalLevel());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("메뉴 저장 성공!!!");
        } else {
            System.out.println("메뉴 저장 실패!!!");
        }

    }
}
