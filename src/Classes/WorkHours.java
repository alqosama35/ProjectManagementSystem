package Classes;
import java.util.Date;

public class WorkHours {
    private Date entryTime;
    private Date exitTime;
    private double duration;

    public WorkHours(Date entryTime, Date exitTime, double duration) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.duration = duration;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}