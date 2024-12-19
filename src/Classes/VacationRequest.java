package Classes;

import Enum.VacationStatus;

import java.util.Date;

public class VacationRequest {
    private static int requestCounter = 0;
    private int requestId;
    private Employee requester;
    private Date startDate;
    private Date endDate;
    private VacationStatus status;

    public VacationRequest(Employee requester, Date startDate, Date endDate) {
        this.requestId = ++requestCounter;
        this.requester = requester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = VacationStatus.Pending;
    }

    public int getRequestId() {
        return requestId;
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
