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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class SecretaryController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/secretary", method = RequestMethod.GET)
    @Order(value = 1)
    public String index(Model model) {
        return "secretary";
    }

    @RequestMapping(value = "/secretary", method = RequestMethod.POST, params = "redirect=consultations")
    public String switchToConsultations()
    {
        return "redirect:/secretaryConsultations";
    }

    @RequestMapping(value = "/secretary", method = RequestMethod.POST, params = "redirect=patients")
    public String switchToPatients()
    {
        return"redirect:/secretaryPatients";
    }

}
