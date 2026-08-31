package keya.internationaltradefairltd;

import keya.internationaltradefairltd.HelperClass.*;
import keya.internationaltradefairltd.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TradeFairSystemTest {

    private DataManager dataManager;

    @BeforeEach
    public void setUp() {
        dataManager = DataManager.getInstance();
    }

    @Test
    public void testDataManagerInitialization() {
        assertNotNull(dataManager.getUsers(), "Users list should not be null");
        assertNotNull(dataManager.getStalls(), "Stalls list should not be null");
        assertNotNull(dataManager.getVendors(), "Vendors list should not be null");
        assertNotNull(dataManager.getMeetings(), "Meetings list should not be null");
        assertNotNull(dataManager.getCustomerQueries(), "Customer queries list should not be null");
        assertNotNull(dataManager.getKnowledgeArticles(), "Knowledge articles should not be null");

        assertTrue(dataManager.getStalls().size() > 0, "Default stalls should be seeded");
        assertTrue(dataManager.getVendors().size() > 0, "Default vendors should be seeded");
    }

    @Test
    public void testUserAuthentication() {
        User manager = dataManager.authenticate("manager", "pass123");
        assertNotNull(manager, "Manager authentication should succeed");
        assertEquals("EventManager", manager.getUserType());

        User agent = dataManager.authenticate("agent", "pass123");
        assertNotNull(agent, "Agent authentication should succeed");
        assertEquals("Customer Support Agent", agent.getUserType());

        User invalid = dataManager.authenticate("wronguser", "wrongpass");
        assertNull(invalid, "Invalid credentials should return null");
    }

    @Test
    public void testVendorApprovalAndStallAssignment() {
        Vendor testVendor = new Vendor(
                "Test Robotics Ltd.", "Asif Khan", "asif@robotics.bd", "01799887766",
                "Robotic arms and AI vision kits showcase", "Corner stall needed", "Pending"
        );
        dataManager.getVendors().add(testVendor);
        assertFalse(testVendor.isApproved(), "Initial status should be pending");

        // Approve vendor
        dataManager.approveVendor(testVendor);
        assertTrue(testVendor.isApproved(), "Vendor status should be Approved");

        // Create a new stall
        Stall stall = new Stall("ST-999", "300", "Innovation Hall", 80000);
        dataManager.getStalls().add(stall);
        assertTrue(stall.isAvailable(), "New stall should be available");

        // Assign stall to vendor
        boolean assigned = dataManager.assignStallToCompany("ST-999", "Test Robotics Ltd.");
        assertTrue(assigned, "Stall assignment should succeed");
        assertFalse(stall.isAvailable(), "Stall should now be marked occupied/assigned");
        assertEquals("Test Robotics Ltd.", stall.getAssignedCompany());
        assertEquals("ST-999", testVendor.getAssignedStallId());
    }

    @Test
    public void testMeetingManagement() {
        int initialCount = dataManager.getMeetings().size();
        Meeting newMeeting = new Meeting("Admin", "03:00 PM", LocalDate.now().plusDays(2));
        dataManager.getMeetings().add(newMeeting);

        assertEquals(initialCount + 1, dataManager.getMeetings().size(), "Meeting count should increase");

        dataManager.getMeetings().remove(newMeeting);
        assertEquals(initialCount, dataManager.getMeetings().size(), "Meeting should be removed");
    }

    @Test
    public void testCustomerQueryLifecycle() {
        CustomerQuery q = new CustomerQuery("QRY-TEST", "Kabir", "kabir@email.com", "Ticketing", "Issue with pass", "Details here");
        dataManager.getCustomerQueries().add(q);
        assertEquals("Open", q.getStatus());

        // Update to in progress
        q.setStatus("In Progress");
        assertEquals("In Progress", q.getStatus());

        // Resolve
        q.setStatus("Resolved");
        q.setResolutionNote("Refund processed.");
        assertEquals("Resolved", q.getStatus());
        assertEquals("Refund processed.", q.getResolutionNote());
    }
}
