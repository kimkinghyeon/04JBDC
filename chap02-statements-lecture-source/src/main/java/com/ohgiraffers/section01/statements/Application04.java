package com.ohgiraffers.section01.statements;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class Application04 {
    public static void main(String[] args) {

//      1. Connection 객체 생성
        Connection con = getConnection();

//      2.Statement 생성
        // 쿼리문을 작성해서 데이터베이스에 요청하기위해 사용하는 객체
        Statement stmt = null;

//      3.ResultSet 생성
        ResultSet rset = null;
//      쿼리문을 사용하기 위해 선언
//      String query = "select emp_id,emp_name from employee where emp_id = '" + empId + "'";

        // 리스트 생성

        List<EmployeeDTO> empList = null;
        
        // EmployeeDTO 생성
        EmployeeDTO row = null;

        try {
//          4. 연결객체의 createStatement()를 이용한 Statement 객체 생성
            stmt = con.createStatement();

            // employee 테이블 전체조회
            String query = "select * from employee";
           
            // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet 에 반환 받기
            rset = stmt.executeQuery(query);
            
            empList = new ArrayList<>();

            // 6. 쿼리문의 결과를 컬럼 이름을 이용해서 사용

                while (rset.next()) {

                    row = new EmployeeDTO();

                    row.setEmpId(rset.getString("EMP_ID"));
                    row.setEmpName(rset.getString("EMP_Name"));
                    row.setEmpNO(rset.getString("EMP_NO"));
                    row.setEmail(rset.getString("Email"));
                    row.setPhone(rset.getString("Phone"));
                    row.setDeptCode(rset.getString("Dept_Code"));
                    row.setJobCode(rset.getString("Job_Code"));
                    row.setSalLevel(rset.getString("Sal_Level"));
                    row.setSalary(rset.getDouble("Salary"));
                    row.setBonus(rset.getDouble("Bonus"));
                    row.setManagerId(rset.getString("Manager_Id"));
                    row.setHireDate(rset.getDate("Hire_Date"));
                    row.setEntDate(rset.getDate("Ent_Date"));
                    row.setEntYn(rset.getString("Ent_Yn"));

                    empList.add(row);
                }
                
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//          7. 사용한 자원 반납
//          선언한 역순으로 차례대로 닫아야함
            close(rset);
            close(stmt);
            close(con);
            
            for(EmployeeDTO emp : empList){
                System.out.println(emp);
            }
        }
    }
}
