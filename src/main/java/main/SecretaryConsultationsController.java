package main;

import main.model.Consultation;
import main.model.Patient;
import main.model.Role;
import main.model.User;
import main.service.ConsultationService;
import main.service.PatientService;
import main.service.UserService;
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

import java.util.List;
import java.util.Optional;

@Controller
public class SecretaryConsultationsController {
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/secretaryConsultations", method = RequestMethod.GET)
    @Order(value = 1)
    public String index(Model model) {
        model.addAttribute("consultation", new Consultation());
        return "secretaryConsultations";
    }

    @RequestMapping(value = "/consultation", method = RequestMethod.POST, params = "action=save")
    public String saveConsultation(@Validated @ModelAttribute("consultation") Consultation consultation, BindingResult bindingResult,Model model)
    {
        if (!bindingResult.hasErrors()){
            Notification<Boolean> saveNotification = consultationService.register(consultation);
            if (saveNotification.hasErrors()){
                model.addAttribute("message", saveNotification.getFormattedErrors());
            }
            updateConsultationsList(model);
        }
        return "secretaryConsultations";
    }

    @RequestMapping(value = "/consultation", method = RequestMethod.POST, params = "action=notifyDoctor")
    public String notifyDoctor(@RequestParam("did") Integer id, Model model)
    {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            if (userOptional.get().getRole().equals(Role.DOCTOR)){
                System.out.println("notify  " + userService.findById(id).get().getId());
            }
            else{
                model.addAttribute("message", "no such doctor");
            }
        }
        else {
            model.addAttribute("message", "no such user");
        }
        model.addAttribute("consultation", new Consultation());
        return "secretaryConsultations";
    }

    @RequestMapping(value = "/consultation", method = RequestMethod.POST, params = "action=findAll")
    public String findAll(Model model)
    {
        updateConsultationsList(model);
        return "secretaryConsultations";
    }

    private void updateConsultationsList(Model model){
        List<Consultation> consultations = consultationService.findAll();
        model.addAttribute("consultationList", consultations);
        model.addAttribute("consultation", new Consultation());
    }

}
