package com.osintsev.market.database.user;

import com.osintsev.market.exception.UserExistsException;
import com.osintsev.market.rest.dto.User;

import java.util.List;

public interface UserService {
    void createUser(User user) throws UserExistsException;
    User getUser(Long id);
    List<User> getUsers();
}
