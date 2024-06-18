package com.ohgiraffers;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class AllEmployee {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        Properties prop  = new Properties();

        try {

            prop.loadFromXML(new FileInputStream
                    ("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("employee");

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();
            empList = new ArrayList<>();

            while (rset.next()) {
// EMP_ID EMP_Name EMP_NO Email Phone Dept_Code Job_Code Sal_Level Salary Bonus Manager_Id Hire_Date Ent_Date Ent_Yn
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
