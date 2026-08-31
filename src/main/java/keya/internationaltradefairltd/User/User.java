package keya.internationaltradefairltd.User;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String userType;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, String confirmPassword, String phoneNumber, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public User(String userName, String password, String firstName, String lastName, String confirmPassword, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.userType = "Customer";
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFullName() {
        String fn = (firstName != null) ? firstName : "";
        String ln = (lastName != null) ? lastName : "";
        String full = (fn + " " + ln).trim();
        return full.isEmpty() ? userName : full;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public static User VerifyUser(ArrayList<User> users, String userName, String password) {
        if (users == null || userName == null || password == null) return null;
        for (User user : users) {
            if (user.getUserName() != null && user.getUserName().equalsIgnoreCase(userName) &&
                user.getPassword() != null && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
