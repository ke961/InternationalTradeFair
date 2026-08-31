package keya.internationaltradefairltd.HelperClass;

import java.io.Serializable;
import java.time.LocalDate;

public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userType;
    private String userName;
    private String feedbackContent;
    private int rating; // 1 to 5
    private LocalDate date;

    public Feedback() {
        this.date = LocalDate.now();
        this.rating = 5;
    }

    public Feedback(String userType, String userName, String feedbackContent) {
        this.userType = userType;
        this.userName = userName;
        this.feedbackContent = feedbackContent;
        this.rating = 5;
        this.date = LocalDate.now();
    }

    public Feedback(String userType, String userName, String feedbackContent, int rating, LocalDate date) {
        this.userType = userType;
        this.userName = userName;
        this.feedbackContent = feedbackContent;
        this.rating = rating;
        this.date = date;
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

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + userType + "] " + userName + ": " + feedbackContent;
    }
}
