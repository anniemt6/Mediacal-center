package com.anna.proj2.pojo;

public class Speciality {

    private final int id;
    private String speciality;

    public Speciality(int id, String speciality){
        this.id = id;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public String getSpeciality(){
        return speciality;
    }
    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }
}
