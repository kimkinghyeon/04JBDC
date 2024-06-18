package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class apptest {
    public static void main(String[] args) {

        Connection con = getConnection();

        //   PreparedStatement - 조건값을 ? 로 두고 다른 쿼리를 미리 컴파일 해둔뒤
        //                       쿼리는 변경하지 않고, 바인딩 되는 변수만 바꿔서 SQL 문이 실행된된다.

        PreparedStatement pstmt = null;

        // select 결과집합을 받아올 용도의 인터페이스
        ResultSet rset = null;

        // 조회할 employee 의 이름 성을 받아서 찾기


        // concat(?, '%') => ? 로 시작하는 것
//        String query = " select * from employee where emp_name like concat( ? , '%' ) ";

        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        Properties prop  = new Properties();

        try {

            // employee-query.xml 가져오는
            // 일반 load 가 아닌 xml 파일이기 떄문에 loadFromXML
            prop.loadFromXML(new FileInputStream
                    ("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query2.xml"));

            // employee-query.xml 안에 있는 커리문 의 키값
            String query = prop.getProperty("selectEmpByFamilyName");


            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();
            empList = new ArrayList<>();

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
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        for (EmployeeDTO emp : empList) {
            System.out.println(emp);
        }

    }
}
