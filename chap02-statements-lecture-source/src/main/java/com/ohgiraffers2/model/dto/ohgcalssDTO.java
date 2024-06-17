package com.ohgiraffers2.model.dto;

public class ohgcalssDTO {
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

    public ohgcalssDTO() {
    }

    public ohgcalssDTO(String sut_no, String sut_name, String gender, String git_id, String email, String mbti, int sub_no) {
        this.sut_no = sut_no;
        this.sut_name = sut_name;
        this.gender = gender;
        this.git_id = git_id;
        this.email = email;
        this.mbti = mbti;
        this.sub_no = sub_no;
    }

    public String getSut_no() {
        return sut_no;
    }

    public void setSut_no(String sut_no) {
        this.sut_no = sut_no;
    }

    public String getSut_name() {
        return sut_name;
    }

    public void setSut_name(String sut_name) {
        this.sut_name = sut_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGit_id() {
        return git_id;
    }

    public void setGit_id(String git_id) {
        this.git_id = git_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public int getSub_no() {
        return sub_no;
    }

    public void setSub_no(int sub_no) {
        this.sub_no = sub_no;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "sut_no='" + sut_no + '\'' +
                ", sut_name='" + sut_name + '\'' +
                ", gender='" + gender + '\'' +
                ", git_id='" + git_id + '\'' +
                ", email='" + email + '\'' +
                ", mbti='" + mbti + '\'' +
                ", sub_no=" + sub_no +
                '}';
    }
}
