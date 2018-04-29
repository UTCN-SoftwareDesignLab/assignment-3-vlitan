package main.service;

import main.model.Patient;
import main.util.Notification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    Notification<Boolean> save(Patient patient);
    Notification<Boolean> delete(Patient patient);
    List<Patient> findAll();
    Optional<Patient> findById(Integer id);
}
