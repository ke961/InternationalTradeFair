package keya.internationaltradefairltd.User;

public class CustomerCareAgent extends User {
    private static final long serialVersionUID = 1L;

    public CustomerCareAgent() {
        super();
        setUserType("Customer Support Agent");
    }

    public CustomerCareAgent(String firstName, String lastName, String userName, String password, String confirmPassword, String phoneNumber) {
        super(firstName, lastName, userName, password, confirmPassword, phoneNumber, "Customer Support Agent");
    }
}
