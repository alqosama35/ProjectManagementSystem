package Classes;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WorkHours implements Serializable {
    private Date entryTime;
    private Date exitTime;
    private String duration;

    public WorkHours() {}

    public WorkHours(Date entryTime, Date exitTime) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        calculateDuration();
    }


    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
        calculateDuration();
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
        calculateDuration();
    }

    public String getDuration() {
        return duration;
    }

    private void calculateDuration() {
        if (entryTime != null && exitTime != null) {
            long durationInMillis = exitTime.getTime() - entryTime.getTime();
            if (durationInMillis < 0) {
                this.duration = "Invalid Times";
            } else {
                long hours = TimeUnit.MILLISECONDS.toHours(durationInMillis);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis) % 60;
                this.duration = hours + " hours " + minutes + " minutes";
            }
        } else {
            this.duration = "Not Set";
        }
    }


    public String toString() {
        return "Entry Time: " + entryTime +
                ", Exit Time: " + exitTime +
                ", Duration: " + duration;
    }
}
