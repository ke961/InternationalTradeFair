package keya.internationaltradefairltd.HelperClass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import keya.internationaltradefairltd.User.CustomerCareAgent;
import keya.internationaltradefairltd.User.EventManager;
import keya.internationaltradefairltd.User.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataManager {
    private static DataManager instance;

    private User currentUser;
    private String selectedUserType = "EventManager";

    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<Stall> stalls = FXCollections.observableArrayList();
    private final ObservableList<Vendor> vendors = FXCollections.observableArrayList();
    private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();
    private final ObservableList<Report> reports = FXCollections.observableArrayList();
    private final ObservableList<Feedback> feedbacks = FXCollections.observableArrayList();
    private final ObservableList<CustomerQuery> customerQueries = FXCollections.observableArrayList();
    private final ObservableList<KnowledgeArticle> knowledgeArticles = FXCollections.observableArrayList();

    private DataManager() {
        initDefaultData();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private void initDefaultData() {
        // Default Users
        users.add(new EventManager("Keya", "Rahman", "manager", "pass123", "pass123", "01711000001"));
        users.add(new CustomerCareAgent("Arif", "Hasan", "agent", "pass123", "pass123", "01811000002"));
        users.add(new User("Tanvir", "Ahmed", "vendor1", "pass123", "pass123", "01911000003", "Vendor"));
        users.add(new User("Sadia", "Islam", "customer1", "pass123", "pass123", "01611000004", "Customer"));
        users.add(new User("Admin", "User", "admin", "pass123", "pass123", "01511000005", "Admin"));
        users.add(new User("Quality", "Inspector", "qcuser", "pass123", "pass123", "01311000006", "Quality Controller"));

        // Default Stalls
        stalls.add(new Stall("ST-101", "250", "Hall A - Front Row", 75000, "Walton Hi-Tech", "Assigned"));
        stalls.add(new Stall("ST-102", "300", "Hall A - Pavilion 2", 90000, "Apex Footwear Ltd.", "Assigned"));
        stalls.add(new Stall("ST-103", "200", "Hall A - Section 3", 55000, "Unassigned", "Available"));
        stalls.add(new Stall("ST-104", "180", "Hall B - Food Court", 45000, "Pran Foods Ltd.", "Assigned"));
        stalls.add(new Stall("ST-105", "350", "Hall B - Electronics", 120000, "Unassigned", "Available"));
        stalls.add(new Stall("ST-106", "220", "Hall C - Textiles", 60000, "Unassigned", "Available"));
        stalls.add(new Stall("ST-107", "400", "International Pavilion", 150000, "Samsung Electronics", "Assigned"));
        stalls.add(new Stall("ST-108", "150", "Hall D - Handicrafts", 35000, "Unassigned", "Available"));

        // Default Vendors / Applicants
        vendors.add(new Vendor(
                "Walton Hi-Tech", "Tanvir Ahmed", "tanvir@waltonbd.com", "01911000003",
                "Smart Refrigerators, ACs, LED Televisions and Home Appliances showcase.",
                "Requires 3-phase high-voltage power line and double frontage banner space.",
                "Approved"
        ));
        vendors.add(new Vendor(
                "Apex Footwear Ltd.", "Rafiqul Islam", "rafiq@apexfootwear.com", "01822334455",
                "Exclusive genuine leather formal & casual shoes, winter collection.",
                "Corner spot preferred with ample lighting support.",
                "Approved"
        ));
        vendors.add(new Vendor(
                "Pran Foods Ltd.", "Nasim Khan", "nasim@prangroup.com", "01733445566",
                "Juices, snacks, confectionery items, fast-food live counter.",
                "Water supply and waste drainage connection required.",
                "Approved"
        ));
        vendors.add(new Vendor(
                "Aarong Crafts", "Farhana Yasmin", "farhana@aarong.com", "01655667788",
                "Handloom sarees, terracotta artifacts, brass showpieces, leather bags.",
                "Extra safety display cases and spotlights requested.",
                "Pending"
        ));
        vendors.add(new Vendor(
                "Singer Bangladesh", "Mahmudul Hasan", "mahmud@singerbd.com", "01599887766",
                "Sewing machines, kitchen appliances, washing machines, microwaves.",
                "Storage area adjoining the stall requested.",
                "Pending"
        ));
        vendors.add(new Vendor(
                "Miyako Appliances", "Kamrul Islam", "kamrul@miyakobd.com", "01344556677",
                "Blenders, air fryers, induction cookers, electric kettles.",
                "Need 10 power sockets for live demonstration.",
                "Pending"
        ));

        // Default Meetings
        meetings.add(new Meeting("Admin", "10:30 AM", LocalDate.now().plusDays(1)));
        meetings.add(new Meeting("Quality Controller", "02:00 PM", LocalDate.now().plusDays(2)));
        meetings.add(new Meeting("Customer Support Agent", "04:30 PM", LocalDate.now().plusDays(3)));
        meetings.add(new Meeting("EventManager", "11:00 AM", LocalDate.now().plusDays(4)));

        // Default Reports
        reports.add(new Report("Vendor", "Walton Hi-Tech", "High Footfall Report", "Stall ST-101 received over 1,500 unique visitors on opening day. Requesting additional security barriers during peak evening hours.", LocalDate.now().minusDays(1), "Reviewed"));
        reports.add(new Report("Customer", "Sadia Islam", "Parking Area Feedback", "The West Gate parking entry experienced severe congestion between 5 PM and 7 PM. Recommend opening Lane 3 for e-ticket holders.", LocalDate.now().minusDays(2), "Investigating"));
        reports.add(new Report("Quality Controller", "qcuser", "Hygiene & Food Safety Audit", "All 18 food court vendors passed baseline cleanliness test. Two stalls given notices for immediate grease trap installation.", LocalDate.now().minusDays(1), "Resolved"));
        reports.add(new Report("Admin", "admin", "Daily Revenue & Gate Sales", "Total day 1 entry gate revenue reached 850,000 BDT with 28,400 tickets scanned.", LocalDate.now().minusDays(1), "Approved"));

        // Default Feedbacks
        feedbacks.add(new Feedback("Vendor", "Walton Hi-Tech", "Excellent stall organization and prompt assistance from the event coordination team. High customer turnout!"));
        feedbacks.add(new Feedback("Customer", "Sadia Islam", "The international pavilion was wonderful. Washrooms were very clean and digital maps were helpful."));
        feedbacks.add(new Feedback("Quality Controller", "qcuser", "Safety drills were executed systematically. Emergency exits and fire extinguishers are properly placed."));
        feedbacks.add(new Feedback("Advertiser", "MediaPlus Agency", "LED billboards at Central Plaza provided outstanding brand visibility. Looking forward to expanding next week."));
        feedbacks.add(new Feedback("Investor", "Prime Capital", "Trade Fair B2B matchmaking sessions showed tremendous potential for export contracts."));

        // Default Customer Queries
        customerQueries.add(new CustomerQuery(
                "QRY-1001", "Rahim Chowdhury", "rahim@gmail.com", "Ticketing",
                "E-Ticket payment debited but QR code not received",
                "I bought 3 VIP tickets via bKash, money was deducted transaction ID #TRX987654, but ticket email is missing.",
                "Open", "", LocalDate.now().minusDays(1)
        ));
        customerQueries.add(new CustomerQuery(
                "QRY-1002", "Sabrina Zaman", "sabrina@outlook.com", "Stall Inquiry",
                "Location of handicraft pavilion & wheelchair access",
                "Is there dedicated wheelchair ramp access for Hall D handicraft pavilion for elderly visitors?",
                "Resolved", "Yes, Ramp 4 at East Gate leads straight into Hall D. Wheelchair assistance is available at Gate 2.", LocalDate.now().minusDays(2)
        ));
        customerQueries.add(new CustomerQuery(
                "QRY-1003", "Kazi Imtiaz", "imtiaz@techbd.com", "Lost & Found",
                "Lost brown leather wallet near Food Court Hall B",
                "Lost my wallet containing NID and credit card on Sunday evening around 6:30 PM near Stall ST-104.",
                "In Progress", "Item logged with Security Booth 2. Searching CCTV footage around Food Court.", LocalDate.now().minusDays(1)
        ));
        customerQueries.add(new CustomerQuery(
                "QRY-1004", "Fahim Shahriar", "fahim@northsouth.edu", "Facilities",
                "Prayer room and drinking water stations map",
                "Could you please share the locations of executive prayer rooms and free drinking water refill points?",
                "Resolved", "Prayer rooms are located on the 2nd floor of Pavilion 1 and North Wing. Pure drinking water stations are available beside every restroom cluster.", LocalDate.now().minusDays(3)
        ));

        // Default FAQs / Knowledge Articles
        knowledgeArticles.add(new KnowledgeArticle("KB-1", "Ticketing", "What are the fair timings and ticket prices?", "General Entry: 50 BDT, Children (under 12): 25 BDT, VIP Pass: 200 BDT. Opening hours: 10:00 AM to 10:00 PM on weekdays, 9:00 AM to 10:30 PM on holidays."));
        knowledgeArticles.add(new KnowledgeArticle("KB-2", "Stall & Vendor", "How do vendors apply for stall allotment?", "Vendors must submit the Vendor Registration Form with trade license and product specifications. Once approved by the Event Manager, a stall is assigned based on size and category."));
        knowledgeArticles.add(new KnowledgeArticle("KB-3", "Facilities", "Are wheelchairs and medical first-aid available on site?", "Yes, free wheelchair assistance and a 24/7 Red Crescent emergency medical camp are situated beside Gate 1 and Gate 3."));
        knowledgeArticles.add(new KnowledgeArticle("KB-4", "Parking", "Where is the designated visitor and VIP parking?", "Multi-level underground parking and open-air North Lot accommodate up to 4,000 vehicles with automated license plate scanning."));
        knowledgeArticles.add(new KnowledgeArticle("KB-5", "Lost & Found", "What is the procedure for claiming lost items?", "Visit Central Security Control Room near Administrative Block with proof of identity and item description."));
    }

    // Getters for ObservableLists
    public ObservableList<User> getUsers() {
        return users;
    }

    public ObservableList<Stall> getStalls() {
        return stalls;
    }

    public ObservableList<Vendor> getVendors() {
        return vendors;
    }

    public ObservableList<Meeting> getMeetings() {
        return meetings;
    }

    public ObservableList<Report> getReports() {
        return reports;
    }

    public ObservableList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public ObservableList<CustomerQuery> getCustomerQueries() {
        return customerQueries;
    }

    public ObservableList<KnowledgeArticle> getKnowledgeArticles() {
        return knowledgeArticles;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getSelectedUserType() {
        return selectedUserType;
    }

    public void setSelectedUserType(String selectedUserType) {
        this.selectedUserType = selectedUserType;
    }

    // Helper business logic methods
    public ObservableList<Stall> getAvailableStalls() {
        ObservableList<Stall> list = FXCollections.observableArrayList();
        for (Stall stall : stalls) {
            if (stall.isAvailable()) {
                list.add(stall);
            }
        }
        return list;
    }

    public ObservableList<Vendor> getApprovedVendors() {
        ObservableList<Vendor> list = FXCollections.observableArrayList();
        for (Vendor vendor : vendors) {
            if (vendor.isApproved()) {
                list.add(vendor);
            }
        }
        return list;
    }

    public ObservableList<Vendor> getPendingVendors() {
        ObservableList<Vendor> list = FXCollections.observableArrayList();
        for (Vendor vendor : vendors) {
            if ("Pending".equalsIgnoreCase(vendor.getStatus())) {
                list.add(vendor);
            }
        }
        return list;
    }

    public boolean assignStallToCompany(String stallId, String companyName) {
        Stall targetStall = null;
        for (Stall stall : stalls) {
            if (stall.getStallId().equalsIgnoreCase(stallId)) {
                targetStall = stall;
                break;
            }
        }
        if (targetStall == null) return false;

        targetStall.setAssignedCompany(companyName);
        for (Vendor v : vendors) {
            if (v.getOrganizationName().equalsIgnoreCase(companyName)) {
                v.setAssignedStallId(stallId);
                break;
            }
        }
        return true;
    }

    public boolean approveVendor(Vendor vendor) {
        if (vendor == null) return false;
        vendor.setStatus("Approved");
        return true;
    }

    public boolean rejectVendor(Vendor vendor) {
        if (vendor == null) return false;
        vendor.setStatus("Rejected");
        return true;
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUserName() != null && user.getUserName().equalsIgnoreCase(username) &&
                user.getPassword() != null && user.getPassword().equals(password)) {
                this.currentUser = user;
                return user;
            }
        }
        // Also check in vendors
        for (Vendor vendor : vendors) {
            if (vendor.getUserName() != null && vendor.getUserName().equalsIgnoreCase(username) &&
                vendor.getPassword() != null && vendor.getPassword().equals(password)) {
                User u = new User(vendor.getFirstName(), vendor.getLastName(), vendor.getUserName(), vendor.getPassword(), vendor.getPassword(), vendor.getPhoneNumber(), "Vendor");
                this.currentUser = u;
                return u;
            }
        }
        return null;
    }
}
