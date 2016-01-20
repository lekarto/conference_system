package com.epam.dao;

import com.epam.status.UpdateStatus;
import com.epam.model.User;
import com.mongodb.WriteResult;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private Logger LOGGER;
    @Autowired
    @Qualifier(value = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Value("${user.columnNames.ID}")
    public String USER_ID;
    @Value("${user.columnNames.Name}")
    public String NAME;
    @Value("${user.columnNames.Password}")
    public String PASSWORD;
    @Value("${user.columnNames.Email}")
    public String EMAIL;


    public User create(User user) {
        LOGGER.debug("UserDaoImpl.create: "+user);
        mongoTemplate.insert(user);
        return user;
    }

    public User findByID(String id) {
        LOGGER.debug("UserDaoImpl.findByID(): id = "+id);
        User user = mongoTemplate.findById(id, User.class);
        LOGGER.debug("answer: "+user);
        return user;
    }

    public User findByName(String name) {
        LOGGER.debug("UserDaoImpl.findByName(): name = "+name);
        User user = mongoTemplate.findOne(new Query(Criteria.where(NAME).is(name)), User.class);
        LOGGER.debug("answer: "+user);
        return user;
    }

    public User findByEmail(String email) {
        LOGGER.debug("UserDaoImpl.findByEmail(): email = "+email);
        User user = mongoTemplate.findOne(new Query(Criteria.where(EMAIL).is(email)), User.class);
        LOGGER.debug("answer: "+user);
        return user;
    }

    public Integer getTotalCount() {
        LOGGER.debug("UserDaoImpl.getTotalCount()");
        return mongoTemplate.findAll(User.class).size();
    }

    public List<User> getAll() {
        LOGGER.debug("UserDaoImpl.getAll()");
        return mongoTemplate.findAll(User.class);
    }

    public UpdateStatus update(User user) {
        LOGGER.debug("UserDaoImpl.update: "+user);
        if (findByID(user.getId()) != null) {
            mongoTemplate.save(user);
            return UpdateStatus.UPDATED;
        } else {
            LOGGER.debug("UserDaoImpl.update: user not found!");
            return UpdateStatus.NOT_FOUND;
        }
    }

    public boolean delete(String userId) {
        WriteResult writeResult = mongoTemplate.remove(new User(userId));
        LOGGER.debug(" Delete result: "+writeResult+" getN()= "+writeResult.getN());
        return (writeResult.getN() > 0);
    }
}