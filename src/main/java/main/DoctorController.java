package main;

import main.model.Consultation;
import main.model.User;
import main.service.ConsultationService;
import main.service.PatientService;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DoctorController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private ConsultationService consultationService;

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    @Order(value = 1)
    public String index() {
        return "doctor";
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST, params = "action=listPatients")
    public String listPatients(Model model) {
        model.addAttribute("patientsList", patientService.findAll());
        return "doctor";
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST, params = "action=listConsultations")
    public String listConsultationsOfPatient(@RequestParam("pid") Integer patientId, Model model) {
        model.addAttribute("consultationList", consultationService.findConsultationsByPatientId(patientId));
        return "doctor";
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST, params = "action=updateConsultation")
    public String updateConsultation(@RequestParam("cid") Integer consultationId, @RequestParam("description") String description, Model model) {
        Optional<Consultation> optionalConsultation = consultationService.findById(consultationId);
        if (optionalConsultation.isPresent()){
            Consultation consultation = optionalConsultation.get();
            consultation.setDescription(description);
            consultationService.save(consultation);
        }
        return "doctor";
    }
}
