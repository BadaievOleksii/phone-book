package com.lardi.phone_book.controller;

import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.service.RecordService;
import com.lardi.phone_book.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewDataController {

    protected static final Logger LOG = LogManager.getLogger(AuthController.class);

    //TODO: crud-operations for records (based on /addrecord, /deleterecord etc, checking for ownerId etc)
    //TODO: view records with searches

    @Autowired
    private RecordService recordService;


    @RequestMapping(value = "/viewdata", method = RequestMethod.GET)
    public String register(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.debug("Logged user id: " + user.getUserId());

        model.addAttribute("records", recordService.getByOwnerId(user.getUserId()));
        return "viewdata";
    }
}
