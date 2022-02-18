package com.anna.proj2.controllers.util;

public class TimetableUi {

    private int id;
    private String dayOfAdmission;
    private String startTime;
    private String endTime;

    public TimetableUi(
            int id,
            String dayOfAdmission,
            String startTime,
            String endTime
    ) {
        this.id = id;
        this.dayOfAdmission = dayOfAdmission;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public String getDayOfAdmission() {
        return dayOfAdmission;
    }
    public void setDayOfAdmission(String dayOfAdmission) {
        this.dayOfAdmission = dayOfAdmission;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
