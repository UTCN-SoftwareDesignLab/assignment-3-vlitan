package main;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorController {

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    @Order(value = 1)
    public String index() {
        return "doctor";
    }
}
