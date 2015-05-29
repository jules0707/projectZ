package house.my.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

  @RequestMapping("/contact")
  public String home() {
    return "contact";
  }

}
