package keya.internationaltradefairltd.HelperClass;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String queryId;
    private String customerName;
    private String customerEmail;
    private String category; // e.g. "Stall Inquiry", "Ticketing", "Facilities", "Security", "Lost & Found"
    private String subject;
    private String details;
    private String status; // "Open", "In Progress", "Resolved"
    private String resolutionNote;
    private LocalDate date;

    public CustomerQuery() {
        this.status = "Open";
        this.date = LocalDate.now();
    }

    public CustomerQuery(String queryId, String customerName, String customerEmail, String category, String subject, String details) {
        this.queryId = queryId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.category = category;
        this.subject = subject;
        this.details = details;
        this.status = "Open";
        this.resolutionNote = "";
        this.date = LocalDate.now();
    }

    public CustomerQuery(String queryId, String customerName, String customerEmail, String category, String subject, String details, String status, String resolutionNote, LocalDate date) {
        this.queryId = queryId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.category = category;
        this.subject = subject;
        this.details = details;
        this.status = status;
        this.resolutionNote = resolutionNote;
        this.date = date;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolutionNote() {
        return resolutionNote;
    }

    public void setResolutionNote(String resolutionNote) {
        this.resolutionNote = resolutionNote;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + queryId + "] (" + status + ") " + subject + " - " + customerName;
    }
}
