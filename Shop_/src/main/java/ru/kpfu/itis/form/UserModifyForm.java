package ru.kpfu.itis.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyForm {

    private User user;
    private UserRole role;
    private boolean is_confirm;
}
