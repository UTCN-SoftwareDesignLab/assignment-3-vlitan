package main.service;

import main.model.Consultation;
import main.model.Patient;
import main.model.User;
import main.repository.ConsultationRepository;
import main.util.Notification;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;

    @Override
    public Notification<Boolean> register(Consultation consultation) {
        Notification<Boolean> result = new Notification<>();
        List<User> doctors = userService.getDoctorsSortedByAvailability();
        if (patientService.findById(consultation.getPatient().getId()).isPresent()) {
                if (doctors.isEmpty()) {
                    result.setResult(Boolean.FALSE);
                    result.addError("All the doctors have left!!");
                } else {
                    consultation.setDoctor(doctors.get(0)); //select the first doctor
                    result.setResult(Boolean.TRUE);
                }
        }
        else{
            result.setResult(Boolean.FALSE);
            result.addError("Mmh, sorry, there is no such patient. register him first.");
        }
        Notification<Boolean> saveResult = this.save(consultation);
        if (saveResult.hasErrors()){
            result.setResult(Boolean.FALSE);
            result.addError(saveResult.getFormattedErrors());
        }
       return result;
    }

    @Override
    public Notification<Boolean> save(Consultation consultation) {
        Notification<Boolean> result = new Notification<>();
        try{
            consultationRepository.save(consultation);
            result.setResult(Boolean.TRUE);
        }
        catch(Exception e){
            result.addError(e.getMessage());
            result.setResult(Boolean.FALSE);
        }
        return result;
    }

    @Override
    public Optional<Consultation> findById(Integer id) {
        return Optional.of(consultationRepository.findOne(id));
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
        return consultationRepository.findAll();
    }
}
