package ru.kpfu.itis.util.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.form.AddWarehouseForm;

public class ValidatorAddWarehouseForm implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddWarehouseForm addWarehouseForm = (AddWarehouseForm) o;
        if (addWarehouseForm.getCity() == null || addWarehouseForm.getCity().isEmpty()) {
            errors.rejectValue("city", "", "Поле не может быть пустым");
        }
        if (addWarehouseForm.getStreet() == null || addWarehouseForm.getStreet().isEmpty()) {
            errors.rejectValue("street", "", "Поле не может быть пустым");
        }
    }
}
