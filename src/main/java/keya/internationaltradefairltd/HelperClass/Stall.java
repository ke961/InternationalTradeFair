package keya.internationaltradefairltd.HelperClass;

import java.io.Serializable;

public class Stall implements Serializable {
    private static final long serialVersionUID = 1L;

    private String stallId;
    private String size; // e.g. "200 sq ft" or "200"
    private String location; // e.g. "Hall A - Section 3"
    private double rent; // e.g. 50000.0
    private String assignedCompany; // e.g. "Walton Hi-Tech", or "Unassigned"
    private String status; // "Available", "Assigned", "Under Maintenance"

    public Stall() {
        this.status = "Available";
        this.assignedCompany = "Unassigned";
    }

    public Stall(String stallId, String size, String location, double rent) {
        this.stallId = stallId;
        this.size = size;
        this.location = location;
        this.rent = rent;
        this.assignedCompany = "Unassigned";
        this.status = "Available";
    }

    public Stall(String stallId, String size, String location, double rent, String assignedCompany, String status) {
        this.stallId = stallId;
        this.size = size;
        this.location = location;
        this.rent = rent;
        this.assignedCompany = assignedCompany;
        this.status = status;
    }

    public String getStallId() {
        return stallId;
    }

    public void setStallId(String stallId) {
        this.stallId = stallId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public String getAssignedCompany() {
        return assignedCompany;
    }

    public void setAssignedCompany(String assignedCompany) {
        this.assignedCompany = assignedCompany;
        if (assignedCompany != null && !assignedCompany.trim().isEmpty() && !assignedCompany.equalsIgnoreCase("Unassigned")) {
            this.status = "Assigned";
        } else {
            this.status = "Available";
            this.assignedCompany = "Unassigned";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return "Available".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return stallId + " (" + location + ", " + size + " sq ft, " + rent + " BDT)";
    }
}
