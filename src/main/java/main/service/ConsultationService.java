package main.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import main.model.Consultation;
import main.model.Patient;
import main.model.User;
import main.util.Notification;
import org.aspectj.weaver.ast.Not;

import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    Notification<Boolean> register(Consultation consultation);
    Notification<Boolean> save(Consultation consultation);
    Optional<Consultation> findById(Integer id);
    List<Consultation> findConsultationsByUser(User user);
    List<Consultation> findConsultationsByPatient(Patient patient);
    List<Consultation> findConsultationsByPatientId(Integer id);
    List<Consultation> findAll();
}
