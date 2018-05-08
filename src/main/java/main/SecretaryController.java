package main;

import main.model.Patient;
import main.model.User;
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

import java.util.List;

@Controller
public class SecretaryController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/secretary", method = RequestMethod.GET)
    @Order(value = 1)
    public String index(Model model) {
        model.addAttribute("patient", new Patient());
        return "secretary";
    }

    @RequestMapping(value = "/patient", method = RequestMethod.POST, params = "action=save")
    public String savePatient(@Validated @ModelAttribute("patient") Patient patient, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Notification<Boolean> saveNotification = patientService.save(patient);
            if (saveNotification.hasErrors()){
                model.addAttribute("message", saveNotification.getFormattedErrors());
            }
            updatePatientList(model);
        }
        return "secretary";
    }

    @RequestMapping(value = "/patient", method = RequestMethod.POST, params = "action=findAll")
    public String findAll(Model model)
    {
        updatePatientList(model);
        return "secretary";
    }

    private void updatePatientList(Model model) {
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patient", new Patient());
        model.addAttribute("patientsList", patients);
    }
}
