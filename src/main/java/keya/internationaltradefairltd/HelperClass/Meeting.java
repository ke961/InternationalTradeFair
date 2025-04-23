package keya.internationaltradefairltd.HelperClass;

import java.time.LocalDate;

public class Meeting {
    private String participant;
    private String time;
    private LocalDate date;


    public Meeting(String participant, String time, LocalDate date) {
        this.participant = participant;
        this.time = time;
        this.date = date;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "participant='" + participant + '\'' +
                ", time='" + time + '\'' +
                ", date=" + date +
                '}';
    }
}
