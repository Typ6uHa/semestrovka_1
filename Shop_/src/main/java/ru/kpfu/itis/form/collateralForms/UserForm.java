package ru.kpfu.itis.form.collateralForms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private long user_id;
    private  String role;
    private boolean is_confirm;

}
