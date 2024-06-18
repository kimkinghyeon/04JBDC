package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {

        /*
        PreparedStatement

        Statement 상속받는 인터페이스
        더 효율적으로 작업이 진행된다.
        완성된  SQL 문과 미완성된 SQL 문을 모두 사용할 수 있다.

        Statement 의 문제점
        1. 에러가 발생하면 쿼리가 그대로 드러난다. ( 보안상 문제가 생김)
        2. 완전한 쿼리를 사용하다 보니, 조작이 가능해진다. ( SQL 인젝션 공격에 노출 )
        3. 많은 요청에 대한 성능 이슈

        위치홀더(Placeholder) : ?
        *  */

        // 커넥션 생성
        Connection con = getConnection();

        // PreparedStatement 레퍼런스 변수 생성
        PreparedStatement pstmt = null;

        //  ResultSet 레퍼런스 변수 생성
        ResultSet rset = null;

        try {
            //  Statement 와 PreparedStatement 다르게 객체를 생성할때 쿼리문을 넣는다.
            pstmt = con.prepareStatement("select emp_id , emp_name from employee");

            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString("emp_id") + ","
                        + rset.getString("emp_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
