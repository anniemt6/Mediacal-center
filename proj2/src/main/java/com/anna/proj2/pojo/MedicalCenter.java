package com.anna.proj2.pojo;


public class MedicalCenter {

    private final int id;
    private String labName;
    private String email;
    private String phoneNum;
    private String address;

    public MedicalCenter(
            int id,
            String labName,
            String email,
            String phoneNum,
            String address
    ) {
        this.id = id;
        this.labName = labName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getLabName(){
        return labName;
    }
    public void setLabName(String labName){
        this.labName = labName;
    }

    public String getEMail(){
        return email;
    }
    public void setEMail(String eMail){
        this.email = eMail;
    }

    public String getPhoneNum(){
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}
