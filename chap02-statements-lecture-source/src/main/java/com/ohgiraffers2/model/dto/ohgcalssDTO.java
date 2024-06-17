package com.ohgiraffers2.model.dto;

import java.sql.Date;

public class EmployeeDTO {
    /*
    DTO 클래스의 조건
    1. 모든 필드는 private
    2. 기본생성자와 모든 필드르 초기화하는 생성자
    3. 모든 필드에 대한  setter/getter
    4. toString Overiding 을 이용한 필드값 반환용 메서드
    5. 직렬화 처리
    * */

    private String sut_no;
    private String sut_name;
    private String gender;
    private String git_id;
    private String email;
    private String mbti;
    private int sub_no;



}
