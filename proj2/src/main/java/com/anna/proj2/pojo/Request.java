package com.anna.proj2.pojo;


public class Request {

    private final int id;
    private final int patientId;
    private final int timetableId;
    private final int doctorId;
    private boolean visit;
    private Time visitTime;

    public Request(
            int id,
            boolean visit,
            Time visitTime,
            int patientId,
            int timetableId,
            int doctorId
    ) {
        this.id = id;
        this.patientId = patientId;
        this.timetableId = timetableId;
        this.doctorId = doctorId;
        this.visit = visit;
        this.visitTime = visitTime;
    }

    public int getId() {
        return id;
    }
    public int getPatientId() {
        return patientId;
    }
    public int getTimetableId() {
        return timetableId;
    }
    public int getDoctorId() {
        return doctorId;
    }

    public boolean getVisit(){
        return visit;
    }
    public void setVisit(boolean visit){
        this.visit = visit;
    }

    public Time getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Time visitTime) {
        this.visitTime = visitTime;
    }
}
