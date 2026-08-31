package keya.internationaltradefairltd.HelperClass;

import java.io.Serializable;
import java.time.LocalDate;

public class Report implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userType;
    private String userName;
    private String reportTitle;
    private String reportContent;
    private LocalDate date;
    private String status;

    public Report() {
        this.date = LocalDate.now();
        this.status = "Submitted";
    }

    public Report(String userType, String userName, String reportTitle, String reportContent) {
        this.userType = userType;
        this.userName = userName;
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
        this.date = LocalDate.now();
        this.status = "Submitted";
    }

    public Report(String userType, String userName, String reportTitle, String reportContent, LocalDate date, String status) {
        this.userType = userType;
        this.userName = userName;
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
        this.date = date;
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + userType + "] " + userName + ": " + reportTitle;
    }
}
