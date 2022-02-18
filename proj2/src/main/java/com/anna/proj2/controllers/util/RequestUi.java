package com.anna.proj2.controllers.util;

public class RequestUi {

    private final int id;
    private final int patientId;
    private String patientName;
    private final int timetableId;
    private final int doctorId;
    private boolean visit;
    private String visitTime;

    public RequestUi(
            int id,
            int patientId,
            int timetableId,
            int doctorId,
            boolean visit,
            String visitTime
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

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getTimetableId() {
        return timetableId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public boolean getVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
