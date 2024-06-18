package com.ohgiraffers.model.dto;

public class DepartementDTO {
    private String Dept_ID;
    private String Dept_Title;
    private String LOCATION_ID;

    public DepartementDTO() {
    }

    public DepartementDTO(String dept_ID, String dept_Title, String LOCATION_ID) {
        Dept_ID = dept_ID;
        Dept_Title = dept_Title;
        this.LOCATION_ID = LOCATION_ID;
    }

    public String getDept_ID() {
        return Dept_ID;
    }

    public void setDept_ID(String dept_ID) {
        Dept_ID = dept_ID;
    }

    public String getDept_Title() {
        return Dept_Title;
    }

    public void setDept_Title(String dept_Title) {
        Dept_Title = dept_Title;
    }

    public String getLOCATION_ID() {
        return LOCATION_ID;
    }

    public void setLOCATION_ID(String LOCATION_ID) {
        this.LOCATION_ID = LOCATION_ID;
    }

    @Override
    public String toString() {
        return "DepartementDTO{" +
                "Dept_ID='" + Dept_ID + '\'' +
                ", Dept_Title='" + Dept_Title + '\'' +
                ", LOCATION_ID='" + LOCATION_ID + '\'' +
                '}';
    }
}
