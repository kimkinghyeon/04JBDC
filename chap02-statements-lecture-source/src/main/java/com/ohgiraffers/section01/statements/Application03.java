package com.ohgiraffers.section01.statements;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

//      1. Connection 객체 생성
        Connection con = getConnection();

//      2.Statement 생성
        Statement stmt = null;

//      3.ResultSet 생성
        ResultSet rset = null;
//      쿼리문을 사용하기 위해 선언
//      String query = "select emp_id,emp_name from employee where emp_id = '" + empId + "'";

        // EmployeeDTO 생성
        EmployeeDTO selectedEmp = null;

        try {
//          4. 연결객체의 createStatement()를 이용한 Statement 객체 생성
            stmt = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.println("조회하려는 사번을 입력하세요 : ");

            String empId = sc.nextLine();
            String query = "select * from employee where emp_id = '" + empId + "'";
            // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet 에 반환 받기
            rset = stmt.executeQuery(query);

            // 6. 쿼리문의 결과를 컬럼 이름을 이용해서 사용
            while (rset.next()) {

                if (rset != null) {
                    selectedEmp = new EmployeeDTO();

                    selectedEmp.setEmpId(rset.getString("EMP_ID"));
                    selectedEmp.setEmpName(rset.getString("EMP_Name"));
                    selectedEmp.setEmpNO(rset.getString("EMP_NO"));
                    selectedEmp.setEmail(rset.getString("Email"));
                    selectedEmp.setPhone(rset.getString("Phone"));
                    selectedEmp.setDeptCode(rset.getString("Dept_Code"));
                    selectedEmp.setJobCode(rset.getString("Job_Code"));
                    selectedEmp.setSalLevel(rset.getString("Sal_Level"));
                    selectedEmp.setSalary(rset.getDouble("Salary"));
                    selectedEmp.setBonus(rset.getDouble("Bonus"));
                    selectedEmp.setManagerId(rset.getString("Manager_Id"));
                    selectedEmp.setHireDate(rset.getDate("Hire_Date"));
                    selectedEmp.setEntDate(rset.getDate("Ent_Date"));
                    selectedEmp.setEntYn(rset.getString("Ent_Yn"));

                    System.out.println(selectedEmp.toString());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//          7. 사용한 자원 반납
//          선언한 역순으로 차례대로 닫아야함
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
