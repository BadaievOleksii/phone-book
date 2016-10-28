package com.lardi.phone_book.controller;

import com.lardi.phone_book.model.entity.Record;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecordsController {

    protected static final Logger LOG = LogManager.getLogger(AuthController.class);

    //TODO: crud-operations for records (based on /addrecord, /deleterecord etc, checking for ownerId etc)
    //TODO: view records with searches

    @Autowired
    private RecordService recordService;


    @RequestMapping(value = "/viewdata", method = RequestMethod.GET)
    public String viewData(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.debug("Logged user id: " + user.getUserId());

        model.addAttribute("records", recordService.getByOwnerId(user.getUserId()));
        return "viewdata";
    }


    @RequestMapping(value = "/deleterecord", method = RequestMethod.GET)
    public String deleteRecord(@RequestParam("id") int id) {

        LOG.debug("Deleting record with id " + id);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Record record = recordService.getByRecordId(Integer.parseInt(id));
        Record record = recordService.getByRecordId(id);
        if(record.getOwnerId() == user.getUserId()){
            recordService.delete(record);
        } else {
            LOG.warn("User " + user.getUsername() + " is trying to delete another user's record");
        }

        return "redirect:/viewdata";
    }
}
