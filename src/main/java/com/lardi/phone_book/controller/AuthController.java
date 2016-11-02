package com.lardi.phone_book.controller;

import com.lardi.phone_book.controller.validator.UserValidator;
import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthController {

    protected static final Logger LOG = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        LOG.debug("Registering user: " + userForm);
        userService.add(userForm);

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        return "login";
    }

}
