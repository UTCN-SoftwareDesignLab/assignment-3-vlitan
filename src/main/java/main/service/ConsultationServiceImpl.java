package main.service;

import main.model.Consultation;
import main.model.Patient;
import main.model.User;
import main.repository.ConsultationRepository;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Override
    public Notification<Boolean> registerConsultation(Consultation consultation) {
        return null;
    }

    @Override
    public List<Consultation> findConsultationsByUser(User user) {
        return null;
    }

    @Override
    public List<Consultation> findConsultationsByPatient(Patient patient) {
        return null;
    }

    @Override
    public List<Consultation> findConsultationsByPatientId(Integer id) {
        return consultationRepository.findConsultationsByPatientId(id);
    }

    @Override
    public List<Consultation> findAll() {
        return null;
    }
}
