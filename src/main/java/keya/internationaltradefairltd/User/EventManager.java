package keya.internationaltradefairltd.User;

public class EventManager extends User {
    private static final long serialVersionUID = 1L;

    public EventManager() {
        super();
        setUserType("EventManager");
    }

    public EventManager(String firstName, String lastName, String userName, String password, String confirmPassword, String phoneNumber) {
        super(firstName, lastName, userName, password, confirmPassword, phoneNumber, "EventManager");
    }
}
