package house.my.web.controllers;

import house.my.model.User;
import house.my.model.enums.controller.UrlEnum;
import house.my.utils.ZProperties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

  @Autowired
  protected ZProperties zProperties;

  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public String setupForm(ModelMap model) {
    model.addAttribute("user", new User());
    return UrlEnum.SIGNIN.url();
  }

  // @RequestMapping(value = "/logout", method = RequestMethod.GET)
  // public String logout(HttpServletRequest request) {
  // return UrlEnum.LOGOUT.url();
  // }

  @ModelAttribute("environnement")
  public String getEnvironnement(HttpServletRequest request) {
    return zProperties.getEnvironnement();
  }
}