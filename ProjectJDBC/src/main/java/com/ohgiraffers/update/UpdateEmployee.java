package com.ohgiraffers.update;

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

public class UpdateEmployee {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 emp id ");
        String empId = sc.next();
        System.out.println("변경할 emp name");
        String empName = sc.next();
        System.out.println("변경할 emp no");
        String empNo = sc.next();
//        System.out.println("변경할 email");
//        String email = sc.next();
//        System.out.println("변경할 phone");
//        String phone = sc.next();
//        System.out.println("변경할 deptCode");
//        String deptCode = sc.next();
        System.out.println("변경할 jobCode");
        String jobCode = sc.next();
        System.out.println("변경할 salLevel");
        String salLevel = sc.next();
//        System.out.println("변경할 Salary ");
//        double salary = sc.nextDouble();
//        System.out.println("변경할 bonus ");
//        double bonus = sc.nextDouble();
//        System.out.println("변경할 ManagerId ");
//        String managerId = sc.next();
//        System.out.println("변경할 hireDate ");
//        Date hireDate = Date.valueOf(sc.next());
//        System.out.println("변경할 EntDate ");
//        Date entDate = Date.valueOf(sc.next());
//        System.out.println("변경할 entYn ");
//        String entYn = sc.next();

//        menuDTO changedMenu = new menuDTO();
//        changedMenu.setMenuCode(menuCode);
//        changedMenu.setMenuName(menuName);
//        changedMenu.setMenuPrice(menuPrice);

        /*------------------------------------------------------------------------------------------------------------*/

        // 쿼리 가져오기

        try {

            EmployeeDTO changedMemder = new EmployeeDTO();

            changedMemder.setEmpId(empId);
            changedMemder.setEmpName(empName);
            changedMemder.setEmpNO(empNo);
//            changedMemder.setEmail(email);
//            changedMemder.setPhone(phone);
//            changedMemder.setDeptCode(deptCode);
            changedMemder.setJobCode(jobCode);
            changedMemder.setSalLevel(salLevel);
//            changedMemder.setSalary(salary);
//            changedMemder.setBonus(bonus);
//            changedMemder.setManagerId(managerId);
////            changedMemder.setHireDate(hireDate);
////            changedMemder.setEntDate(entDate);
//            changedMemder.setEntYn(entYn);

            prop.loadFromXML(new FileInputStream
                    ("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("updateEmployee");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,changedMemder.getEmpName());
            pstmt.setString(2,changedMemder.getEmpNO());
            pstmt.setString(3,changedMemder.getJobCode());
            pstmt.setString(4,changedMemder.getSalLevel());
            pstmt.setString(5,changedMemder.getEmpId());

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
