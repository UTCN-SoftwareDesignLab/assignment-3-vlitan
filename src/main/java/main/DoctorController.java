package main;

import main.model.Consultation;
import main.model.DTO.DoctorNotification;
import main.model.DTO.DoctorNotificationResponse;
import main.model.Role;
import main.model.User;
import main.repository.UserRepository;
import main.service.ConsultationService;
import main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private UserRepository userService;

    private Model model;



    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    @Order(value = 1)
    public String index(Model model) {
        this.model = model;
        this.model.addAttribute("message", "merge");
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

    @MessageMapping("/notifyDoctor")
    @SendTo("/topic/errors")
    //@SendTo("/topic/notification")
    public DoctorNotificationResponse gotNotified(DoctorNotification message){
        System.out.println("[DoctorController] got notified: " + message.getUserId());
        Integer id = new Integer(message.getUserId());
        Optional<User> userOptional = userService.findById(id);
        DoctorNotificationResponse doctorNotificationError = new DoctorNotificationResponse();
        if (userOptional.isPresent()){
            if (userOptional.get().getRole().equals(Role.DOCTOR)){
                doctorNotificationError.setResponse("Doctor " + userOptional.get().getUsername() + " please welcome your patient");
                //result.setResult("Doctor " + userOptional.get().getUsername() + " please welcome your patient");
                System.out.println("notify  " + userService.findById(id).get().getId());
            }
            else{
                doctorNotificationError.addError("no such doctor");
            }
        }
        else {
            doctorNotificationError.addError("no such user");
        }
        return doctorNotificationError;
    }
}
