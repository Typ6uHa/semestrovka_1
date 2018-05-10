package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.UserModifyForm;
import ru.kpfu.itis.model.User;

public class UserModifyFormTransform {

    public static User transform(UserModifyForm form) {
        User user_out = new User();
        User user = form.getUser();
        user_out.setId(user.getId());
        user_out.setUsername(user.getUsername());
        user_out.setEmail(user.getEmail());
        user_out.setPassword(user.getPassword());
        user_out.setRole(form.getRole());
        user_out.set_confirm(form.is_confirm());
        return user_out;
    }
}
