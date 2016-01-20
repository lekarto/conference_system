package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.status.UpdateStatus;
import com.epam.model.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private Logger LOGGER;
    @Autowired
    private UserDao userDao;

    public List<User> getList() {
        return userDao.getAll();
    }

    public User getById(String id) {
        LOGGER.debug("UserServiceImpl.getById: "+id);
        return userDao.findByID(id);
    }

    @Override
    public User getByName(String name) {
        LOGGER.debug("UserServiceImpl.getByName: "+name);
        return userDao.findByName(name);
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.debug("UserServiceImpl.getByEmail: "+email);
        return userDao.findByEmail(email);
    }

    public Integer getTotalCount() {
        LOGGER.debug("UserServiceImpl.getTotalCount()");
        return userDao.getTotalCount();
    }


    public User create(User user) {
        LOGGER.debug("UserServiceImpl.add: "+user);
        return userDao.create(user);
    }

    public boolean delete(String id) {
        LOGGER.debug("UserServiceImpl.delete: "+id);
        return userDao.delete(id);
    }

    public UpdateStatus update(User user) {
        LOGGER.debug("UserServiceImpl.update: "+user);
        return userDao.update(user);
    }
}
