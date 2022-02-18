package com.anna.proj2.pojo;

public class Doctor {

    private final int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNum;
    private String doctorPassword;
    private String doctorEmail;
    private final String speciality;
    private final String medicalCenterName;
    private final String departmentName;
    private final String officeNumber;

    public Doctor(
            int id,
            String firstName,
            String lastName,
            String middleName,
            String phoneNum,
            String doctorPassword,
            String doctorEmail,
            String speciality,
            String medicalCenterName,
            String departmentName,
            String officeNumber
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNum = phoneNum;
        this.doctorPassword = doctorPassword;
        this.doctorEmail = doctorEmail;
        this.speciality = speciality;
        this.medicalCenterName = medicalCenterName;
        this.departmentName = departmentName;
        this.officeNumber = officeNumber;
    }

    public int getId() {
        return id;
    }
    public String getSpeciality() {
        return speciality;
    }
    public String getMedicalCenterName() {
        return medicalCenterName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public String getOfficeNumber() {
        return officeNumber;
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

    public String getDoctorPassword() {
        return doctorPassword;
    }
    public void setDoctorPassword(String doctorPassword){
        this.doctorPassword = doctorPassword;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }
    public void setDoctorEmail(String doctorEmail){
        this.doctorEmail = doctorEmail;
    }
}
