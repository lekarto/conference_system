package com.epam.dao;

import com.epam.status.UpdateStatus;
import com.epam.model.User;

import java.util.List;

public interface UserDao {
    User create(User user);
    User findByID(String id);
    User findByName(String name);
    User findByEmail(String email);
    Integer getTotalCount();
    List<User> getAll();
    UpdateStatus update(User user);
    boolean delete(String userId);
}