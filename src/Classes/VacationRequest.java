package Classes;

import Enum.VacationStatus;

import java.util.Date;

public class VacationRequest {
    private Employee requester;
    private Date startDate;
    private Date endDate;
    private VacationStatus status = VacationStatus.Pending;

    public VacationRequest(Employee requester, Date startDate, Date endDate) {
        this.requester = requester;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Employee getRequester() {
        return requester;
    }

    public void setRequester(Employee requester) {
        this.requester = requester;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public VacationStatus getStatus() {
        return status;
    }

    public void setStatus(VacationStatus status) {
        this.status = status;
    }

    public VacationStatus approve (){
        this.status = VacationStatus.Approved;
        return status;
    }

    public VacationStatus reject (){
        this.status = VacationStatus.Rejected;
        return status;
    }
}
