package main.service;

import main.model.Patient;
import main.repository.PatientRepository;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Notification<Boolean> save(Patient patient) {
        Notification<Boolean> result = new Notification<>();
        try {
            patientRepository.save(patient);
            result.setResult(Boolean.TRUE);
        }
        catch(Exception e){
            result.addError(e.getMessage());
            result.setResult(Boolean.FALSE);
        }
        return result;
    }

    @Override
    public Notification<Boolean> delete(Patient patient) {
        return null;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Integer id) {
        return Optional.of(patientRepository.findOne(id));
    }
}
