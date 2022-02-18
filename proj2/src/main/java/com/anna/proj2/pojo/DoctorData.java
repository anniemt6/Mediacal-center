package com.anna.proj2.pojo;

public class DoctorData {

    private int officeId;
    private int departmentId;
    private int specialityId;
    private int medId;

    public DoctorData(int officeId, int departmentId, int specialityId, int medId) {
        this.officeId = officeId;
        this.departmentId = departmentId;
        this.specialityId = specialityId;
        this.medId = medId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }
}
