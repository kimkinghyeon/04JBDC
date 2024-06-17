package com.ohgiraffers2;

import com.ohgiraffers2.model.dto.ohgcalssDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers2.config.JDBCTemplate.close;
import static com.ohgiraffers2.config.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

//      1. Connection 객체 생성
        Connection con = getConnection();

//      2.Statement 생성
        Statement stmt = null;

//      3.ResultSet 생성
        ResultSet rset = null;
//      쿼리문을 사용하기 위해 선언
//      String query = "select emp_id,emp_name from employee where emp_id = '" + empId + "'";

        // 리스트 생성

        List<ohgcalssDTO> ohgcallsList = null;

        // ohgcallsDTO 생성
        ohgcalssDTO row = null;

        try {
//          4. 연결객체의 createStatement()를 이용한 Statement 객체 생성
            stmt = con.createStatement();

            // employee 테이블 전체조회
            String query = "select * from ohclass";

            // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet 에 반환 받기
            rset = stmt.executeQuery(query);

            ohgcallsList = new ArrayList<>();

            // 6. 쿼리문의 결과를 컬럼 이름을 이용해서 사용

            while (rset.next()) {

                row = new ohgcalssDTO();

                row.setSut_no(rset.getString("sut_no"));
                row.setSut_name(rset.getString("sut_name"));
                row.setGender(rset.getString("gender"));
                row.setGit_id(rset.getString("git_id"));
                row.setEmail(rset.getString("email"));
                row.setMbti(rset.getString("mbti"));
                row.setSub_no(rset.getInt("sub_no"));

                ohgcallsList.add(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//          7. 사용한 자원 반납
//          선언한 역순으로 차례대로 닫아야함
            close(rset);
            close(stmt);
            close(con);

            for (ohgcalssDTO ohg : ohgcallsList) {
                System.out.println(ohg);
            }
        }
    }
}
