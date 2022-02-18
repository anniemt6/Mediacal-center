package com.anna.proj2.pojo;

public class DoctorOffice {

    private final int id;
    private String officeNum;

    public DoctorOffice(int id, String officeNum){
        this.id = id;
        this.officeNum = officeNum;
    }

    public int getId() {
        return id;
    }

    public String getOfficeNum(){
        return officeNum;
    }
    public void setOfficeNum(String officeNum){
        this.officeNum = officeNum;
    }
}
