package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.validators.ValidatorForm;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    private ValidatorForm validatorForm = new ValidatorForm();

    @RequestMapping(value = "/")
    public String getIndexPage() {
        return "home";
    }

    @RequestMapping(value = "/forbidden")
    public String getForbiddenPage() {
        return "404";
    }

    @RequestMapping(value = "/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userform", new UserRegistrationForm());
        model.addAttribute("error", "");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userform") @Valid UserRegistrationForm form, BindingResult result, Model model) {
        validatorForm.validate(form, result);
        if (userService.getOneByUsername(form.getUsername())!= null){
            model.addAttribute("userform", form);
            model.addAttribute("error", "Имя занято");
        }
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.save(form);
            return "redirect:/";
        }
    }

}