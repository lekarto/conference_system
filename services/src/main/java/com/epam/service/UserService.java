package com.epam.service;

import com.epam.model.User;
import com.epam.status.UpdateStatus;

import java.util.List;

public interface UserService {
    List<User> getList();
    User getById(String id);
    User getByName(String name);
    User getByEmail(String email);
    Integer getTotalCount();
    User create(User user);
    boolean delete(String id);
    UpdateStatus update(User user);
}
