package keya.internationaltradefairltd.HelperClass;

import java.io.Serializable;

public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String organizationName;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String productDetails;
    private String specialRequest;
    private String status; // "Pending", "Approved", "Rejected"
    private String assignedStallId; // e.g. "ST-101"

    public Vendor() {
        this.status = "Pending";
        this.assignedStallId = "None";
    }

    public Vendor(String organizationName, String firstName, String lastName, String userName, String password, String phoneNumber, String email, String productDetails, String specialRequest) {
        this.organizationName = organizationName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.productDetails = productDetails;
        this.specialRequest = specialRequest;
        this.status = "Pending";
        this.assignedStallId = "None";
    }

    public Vendor(String organizationName, String applicantName, String email, String phoneNumber, String productDetails, String specialRequest, String status) {
        this.organizationName = organizationName;
        if (applicantName != null) {
            String[] parts = applicantName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = parts.length > 1 ? parts[1] : "";
        }
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.productDetails = productDetails;
        this.specialRequest = specialRequest;
        this.status = status;
        this.assignedStallId = "None";
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    // Property aliases for JavaFX PropertyValueFactory
    public String getCompany() {
        return organizationName;
    }

    public String getCompanyName() {
        return organizationName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getApplicantName() {
        String fn = (firstName != null) ? firstName : "";
        String ln = (lastName != null) ? lastName : "";
        String full = (fn + " " + ln).trim();
        return full.isEmpty() ? (userName != null ? userName : organizationName) : full;
    }

    public String getContactPerson() {
        return getApplicantName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email != null ? email : (userName != null ? userName + "@ditf.org" : "info@" + (organizationName != null ? organizationName.toLowerCase().replace(" ", "") : "vendor") + ".com");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public String getRequests() {
        return specialRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedStallId() {
        return assignedStallId;
    }

    public void setAssignedStallId(String assignedStallId) {
        this.assignedStallId = assignedStallId;
    }

    public boolean isApproved() {
        return "Approved".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return organizationName + " (" + getApplicantName() + " - " + status + ")";
    }
}
