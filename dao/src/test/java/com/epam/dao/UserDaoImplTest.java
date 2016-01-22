package com.epam.dao;

import com.epam.config.AppConfig;
import com.epam.model.User;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@ActiveProfiles("test")
public class UserDaoImplTest {

    @Autowired
    private Logger LOGGER;

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAll() {
        LOGGER.debug("test: getUsers()");
        List<User> users = userDao.getAll();
        LOGGER.debug("test: getUsers() count = "+users.size());
        Assert.assertTrue(users.size() == 2);
    }

    @Test
    public void testCreate() {
        LOGGER.debug("test: testCreate()");
        User user = userDao.create(new User("Semen", "42342", "semen@semen.com"));
        LOGGER.debug("test: testCreate() user after creation: "+user);
        Assert.assertNotNull(user);
    }
}