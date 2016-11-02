package com.lardi.phone_book.controller;

import com.lardi.phone_book.controller.validator.RecordValidator;
import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.service.RecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecordsController {

    protected static final Logger LOG = LogManager.getLogger(AuthController.class);

    //TODO: view records with searches

    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordValidator recordValidator;


    @RequestMapping(value = "/viewdata", method = RequestMethod.GET)
    public String viewData(@RequestParam(value = "surname", required = false) String surnameParam,
                           @RequestParam(value = "name", required = false) String nameParam,
                           @RequestParam(value = "mobile", required = false) String mobileParam,
                           Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.debug("Logged user id: " + user.getUserId());



        List<Record> dbRecordsList = recordService.getByOwnerId(user.getUserId());
        List<Record> recordsList = new ArrayList<>();

        if(surnameParam == null && nameParam == null && mobileParam == null){
            //If no filter params - list all records
            recordsList = dbRecordsList;
        } else {
            if (surnameParam == null) {
                surnameParam = "";
            }
            if (nameParam == null) {
                nameParam = "";
            }
            if (mobileParam == null) {
                mobileParam = "";
            }

            for (Record record : dbRecordsList) {
                if ((record.getSurname().contains(surnameParam) && !surnameParam.isEmpty()) ||
                        (record.getName().contains(nameParam) && !nameParam.isEmpty()) ||
                        (record.getMobilePhone().contains(mobileParam) && !mobileParam.isEmpty())) {
                    recordsList.add(record);
                }
            }
        }

        model.addAttribute("records", recordsList);
        return "viewdata";
    }




    @RequestMapping(value = "/addrecord", method = RequestMethod.GET)
    public String addRecord(Model model) {
        Record record = new Record();
        model.addAttribute("recordForm", record);

        return "addrecord";

    }
    @RequestMapping(value = "/addrecord", method = RequestMethod.POST)
    public String addRecord(@ModelAttribute("recordForm") Record record, BindingResult bindingResult, Model model) {

        recordValidator.validate(record, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addrecord";
        }


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        record.setOwnerId(user.getUserId());

        LOG.debug("Adding new record: " + record);

        recordService.add(record);

        return "redirect:/viewdata";

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


        Record dbRecord = recordService.getByRecordId(record.getRecordId());
        if(dbRecord == null){
            LOG.debug("Cannot update - there is no record with such id in DB: " + record.getRecordId());
            return "redirect:/viewdata";
        }

        recordValidator.validate(record, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updaterecord";
        }

        LOG.debug("Record for updating: " + record);


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


        Record record = recordService.getByRecordId(id);
        if(record == null){
            LOG.debug("No record with id " + id + " - nothing to delete");
            return "redirect:/viewdata";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LOG.debug("Deleting record with id " + id);
        if(record.getOwnerId() == user.getUserId()){
            recordService.delete(record);
        } else {
            LOG.warn("User " + user.getUsername() + " is trying to delete another user's record");
        }

        return "redirect:/viewdata";
    }
}
