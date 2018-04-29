package main.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import main.model.Consultation;
import main.model.User;
import main.util.Notification;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface ConsultationService {
    Notification<Boolean> registerConsultation(Consultation consultation);
    List<Consultation> findConsultationsByUser(User user);
    List<Consultation> findConsultationsByPatient(User user);
    List<Consultation> findAll();
}
