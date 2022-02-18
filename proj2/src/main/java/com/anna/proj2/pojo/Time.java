package com.anna.proj2.pojo;

public class Time implements Comparable<Time> {

    private byte hours;
    private byte minutes;

    public Time(byte hours, byte minutes) {

        if (hours >= 0 && hours <= 23 ){
            this.hours = hours;
        } else {
            throw new IllegalArgumentException("Hours can be between 0 and 23 only");
        }

        if (minutes >= 0 && minutes <= 59 ){
            this.minutes = minutes;
        } else {
            throw new IllegalArgumentException("Minutes can be between 0 and 59 only");
        }
    }

    public byte getHours() {
        return hours;
    }

    public void setHours(byte hours) {
        if (hours >= 0 && hours <= 23 ){
            this.hours = hours;
        } else {
            throw new IllegalArgumentException("Hours can be between 0 and 23 only");
        }
    }

    public byte getMinutes() {
        return minutes;
    }

    public void setMinutes(byte minutes) {
        if (minutes >= 0 && minutes <= 59 ){
            this.minutes = minutes;
        } else {
            throw new IllegalArgumentException("Minutes can be between 0 and 59 only");
        }
    }

    public Time addMinutes(int minutes) {

        int newMinutes = hours * 60 + this.minutes + minutes;

        byte nHours = (byte) (newMinutes / 60);
        byte nMinutes = (byte) (newMinutes - hours * 60);

        if (nMinutes >= 60) {
            nMinutes -= 60;
        }

        return new Time(nHours, nMinutes);
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Time)) {
            return false;
        }

        Time time = (Time) obj;

        return time.hours == this.hours && time.minutes == this.minutes;
    }

    @Override
    public int compareTo(Time time) {

        int otherMinutes = time.hours * 60 + time.minutes;
        int thisMinutes = hours * 60 + minutes;

        return thisMinutes - otherMinutes;
    }

    @Override
    public String toString() {
        return hours + ":" + (minutes == 0 ? "00" : minutes);
    }
}
