package main.model.DTO;

public class DoctorNotification {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public DoctorNotification() {
    }

    public DoctorNotification(int content) {
        this.userId = content;

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
