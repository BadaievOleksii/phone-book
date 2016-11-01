package com.lardi.phone_book.controller.validator;

import com.lardi.phone_book.model.dao.UserDao;
import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class RecordValidator implements Validator {

    private static final String SURNAME_REGEX="^[a-zA-Z]+$";
    private static final String NAME_REGEX="^[a-zA-Z]+$";
    private static final String PATRONYMIC_REGEX="^[a-zA-Z]+$";
    private static final String MOBILEPHONE_REGEX="^\\+38\\(0[0-9]{2}\\)[0-9]{7}$";
    private static final String HOMEPHONE_REGEX="^[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}$";

    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private boolean checkWithRegExp(String str, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @Override
    public void validate(Object o, Errors errors) {
        Record record = (Record) o;


        if (record.getSurname().length() < 4 || record.getSurname().length() > 45) {
            errors.rejectValue("surname", "Size.recordForm.surname");
        }
        if (record.getName().length() < 4 || record.getName().length() > 45) {
            errors.rejectValue("name", "Size.recordForm.name");
        }
        if (record.getPatronymic().length() < 4 || record.getPatronymic().length() > 45) {
            errors.rejectValue("patronymic", "Size.recordForm.patronymic");
        }

        if (!checkWithRegExp(record.getSurname(), SURNAME_REGEX)) {
            errors.rejectValue("surname", "Symbols.recordForm.surname");
        }
        if (!checkWithRegExp(record.getName(), NAME_REGEX)) {
            errors.rejectValue("name", "Symbols.recordForm.name");
        }
        if (!checkWithRegExp(record.getPatronymic(), PATRONYMIC_REGEX)) {
            errors.rejectValue("patronymic", "Symbols.recordForm.patronymic");
        }
        if (!checkWithRegExp(record.getMobilePhone(), MOBILEPHONE_REGEX)) {
            errors.rejectValue("mobilePhone", "Symbols.recordForm.mobilePhone");
        }
        if(record.getHomePhone() !=  null) {
            if (!checkWithRegExp(record.getHomePhone(), HOMEPHONE_REGEX)) {
                errors.rejectValue("homePhone", "Symbols.recordForm.homePhone");
            }
        }

        if(record.getEmail() != null) {
            if (!checkWithRegExp(record.getEmail(), EMAIL_REGEX)) {
                errors.rejectValue("email", "Symbols.recordForm.email");
            }
        }

        if(record.getAddress() != null){
            if (record.getAddress().length() > 45) {
                errors.rejectValue("address", "Size.recordForm.address");
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

}
