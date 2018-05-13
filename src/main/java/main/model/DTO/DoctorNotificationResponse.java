package main.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class DoctorNotificationResponse {

    private List<String> errors = new ArrayList<>();
    private String response;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void addError(String error){
        errors.add(error);
    }
}
