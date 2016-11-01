package com.lardi.phone_book.controller;

import com.lardi.phone_book.controller.validator.RecordValidator;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    private RecordValidator recordValidator;


    @RequestMapping(value = "/viewdata", method = RequestMethod.GET)
    public String viewData(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.debug("Logged user id: " + user.getUserId());

        model.addAttribute("records", recordService.getByOwnerId(user.getUserId()));
        return "viewdata";
    }

    @RequestMapping(value = "/updaterecord", method = RequestMethod.GET)
    public String updateRecord(@RequestParam(value = "id", required = true) Integer id, Model model) {

        Record record = recordService.getByRecordId(id);
        if(record == null){
            LOG.debug("Cannot update - there is no record with such id in DB: " + id);
            return "redirect:/viewdata";
        }

        LOG.debug("Record before jsp page: " + record);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(record.getOwnerId() == user.getUserId()){
            model.addAttribute("recordForm", record);
            return "updaterecord";
        } else {
            LOG.warn("User " + user.getUsername() + " is trying to update another user's record");
            return "redirect:/viewdata";
        }

    }
    @RequestMapping(value = "/updaterecord", method = RequestMethod.POST)
    public String updateRecord(@ModelAttribute("recordForm") Record record, BindingResult bindingResult, Model model) {

        LOG.debug("Record for updating: " + record);


        Record dbRecord = recordService.getByRecordId(record.getRecordId());
        if(dbRecord == null){
            LOG.debug("Cannot update - there is no record with such id in DB: " + record.getRecordId());
            return "redirect:/viewdata";
        }

        recordValidator.validate(record, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updaterecord";
        }


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (dbRecord.getOwnerId() == user.getUserId()) {
            record.setOwnerId(dbRecord.getOwnerId());
            recordService.update(record);
        } else {
            LOG.warn("User " + user.getUsername() + " is trying to update another user's record");
        }
        return "redirect:/viewdata";

    }






    @RequestMapping(value = "/deleterecord", method = RequestMethod.GET)
    public String deleteRecord(@RequestParam(value = "id", required = true) Integer id) {

        LOG.debug("Deleting record with id " + id);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Record record = recordService.getByRecordId(Integer.parseInt(id));
        Record record = recordService.getByRecordId(id);
        //for null record
        if(record.getOwnerId() == user.getUserId()){
            recordService.delete(record);
        } else {
            LOG.warn("User " + user.getUsername() + " is trying to delete another user's record");
        }

        return "redirect:/viewdata";
    }
}
