package ru.kpfu.itis.util.validators;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.form.UserRegistrationForm;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorForm implements Validator {

    private Pattern username = Pattern.compile("[A-Z, a-z, _, -]{3,15}");
    private Pattern password = Pattern.compile("[A-Z, a-z, 0-9]{6,20}");
    private Pattern email = Pattern.compile("[A-Z, a-z, 0-9, -, _, .]+@[a-z]+.[a-z]+");

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegistrationForm userForm = (UserRegistrationForm) o;
        if (userForm.getUsername() == null || userForm.getUsername().isEmpty()) {
            errors.rejectValue("username", "", "Поле не может быть пустым");
        }
        if (userForm.getEmail() == null || userForm.getEmail().isEmpty()) {
            errors.rejectValue("email", "", "Поле не может быть пустым");
        }
        if (userForm.getPassword() == null || userForm.getPassword().isEmpty()) {
            errors.rejectValue("password", "", "Поле не может быть пустым");
        }
        if (userForm.getRepassword() == null || userForm.getRepassword().isEmpty()) {
            errors.rejectValue("repassword", "", "Поле не может быть пустым");
        }

        Matcher matcher_username = username.matcher(userForm.getUsername());
        Matcher matcher_password = password.matcher(userForm.getPassword());
        Matcher matcher_repassword = password.matcher(userForm.getRepassword());
        Matcher matcher_email = email.matcher(userForm.getEmail());

        if (!matcher_username.matches()) {
            errors.rejectValue("username", "", "Имя не корректно");
        }

        if (!matcher_password.matches()) {
            errors.rejectValue("password", "", "Не корректный ввод пароля");
        }

        if (!matcher_repassword.matches() || !Objects.equals(userForm.getPassword(), userForm.getRepassword())) {
            errors.rejectValue("repassword", "", "Не корректный ввод пароля");
        }

        if (!matcher_email.matches()) {
            errors.rejectValue("email", "", "Не корректный ввод email");
        }
    }

}
