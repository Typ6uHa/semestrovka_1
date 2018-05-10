package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.UserModifyForm;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.model.User;

import java.util.List;

@Service
public interface UserService {
    User getOneByUsername(String username);

    List<User> getAll();

    void save(UserRegistrationForm form);

    boolean activatedUser(String token);

    User getById(long id);

    void modifyUser(UserModifyForm form);

    void delete(User user);
}
