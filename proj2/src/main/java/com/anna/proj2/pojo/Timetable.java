package com.anna.proj2.pojo;

import java.util.Date;

public class Timetable {

    private final int id;
    private Date dayOfAdmission;
    private Time startTime;
    private Time endTime;

    public Timetable(
            int id,
            Date dayOfAdmission,
            Time startTime,
            Time endTime
    ) {
        this.id = id;
        this.dayOfAdmission = dayOfAdmission;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public Date getDayOfAdmission(){
        return dayOfAdmission;
    }
    public void setDayOfAdmission(Date dayOfAdmission){
        this.dayOfAdmission = dayOfAdmission;
    }

    public Time getStartTime(){
        return startTime;
    }
    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    public Time getEndTime(){
        return endTime;
    }
    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }
}
