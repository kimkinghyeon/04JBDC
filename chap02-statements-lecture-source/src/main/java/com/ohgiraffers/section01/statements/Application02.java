package com.ohgiraffers.section01.statements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.config.JDBCTemplate.close;
import static com.ohgiraffers.config.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {


        Connection con = getConnection();


        Statement stmt = null;


        ResultSet rset = null;

        try {

            stmt = con.createStatement();

            rset = stmt.executeQuery("select emp_id,emp_name from employee");

            while (rset.next()){

                System.out.println(rset.getString("EMP_ID") + ","  + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(rset);
            close(stmt);
            close(con);
        }
    }
}
