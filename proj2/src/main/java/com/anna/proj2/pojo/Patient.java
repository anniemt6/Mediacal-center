package com.anna.proj2.pojo;

import java.util.Date;

public class Patient {

    private final int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNum;
    private Date dateOfBirth;
    private String address;
    private String sex;
    private int medicalHistoryNum;
    private final String patientPassword;
    private final String patientEmail;

    public Patient(
            int id,
            String firstName,
            String lastName,
            String middleName,
            String phoneNum,
            Date dateOfBirth,
            String address,
            String sex,
            String patientPassword,
            String patientEmail,
            int medicalHistoryNum
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNum = phoneNum;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.sex = sex;
        this.patientPassword = patientPassword;
        this.patientEmail = patientEmail;
        this.medicalHistoryNum = medicalHistoryNum;
    }

    public int getId() {
        return id;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getMiddleName(){
        return middleName;
    }
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    public String getPhoneNum(){
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }

    public int getMedicalHistoryNum(){
        return medicalHistoryNum;
    }
    public void setMedicalHistoryNum(int medicalHistoryNum){
        this.medicalHistoryNum = medicalHistoryNum;
    }

    public String getPatientPassword(){
        return patientPassword;
    }
    public void setPatientPassword(String patientPassword){
        this.sex = patientPassword;
    }

    public String getPatientEmail(){
        return patientEmail;
    }
    public void setPatientEmail(String patientEmail){
        this.sex = patientEmail;
    }
}
