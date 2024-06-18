package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.DepartementDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class Application04 {
    public static void main(String[] args) {

        /*
        Department 테이블의 부서코드(아이디)를 입력받아서
        부서코드(아이디),부서명,지역코드를 출력하시오
        * * */
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;


        Scanner sc = new Scanner(System.in);
        System.out.print(" 부서코드 : ");
        String Dept_ID = sc.nextLine();

        DepartementDTO DepartementDTO = null;

        try {

            pstmt = con.prepareStatement(" select * from department where Dept_ID = ? ");

            pstmt.setString(1,Dept_ID);

            rset = pstmt.executeQuery();

            if(rset.next()){
                DepartementDTO = new DepartementDTO();

                DepartementDTO.setDept_ID(rset.getString("dept_id"));
                DepartementDTO.setDept_Title(rset.getString("dept_title"));
                DepartementDTO.setLOCATION_ID(rset.getString("location_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        System.out.println("DepartementDTO = " + DepartementDTO);
    }
}
