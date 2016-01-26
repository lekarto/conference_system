package com.epam.config;

import com.epam.dao.UserDao;
import com.epam.dao.UserDaoImpl;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;


@Configuration
@Profile("test")
public class AppConfig {

    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger();
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate getMongoTemplate() {
        MongoTemplate mongoTemplate = Mockito.mock(MongoTemplate.class);
        List<User> allUsers = new ArrayList<User>() {{
            add(new User("Petro", "22"));
            add(new User("2323derfvb", "22234"));
        }};
        for (int i = 0; i < allUsers.size(); i++) {
            allUsers.get(i).setId((String.valueOf(i+100)));
        }
        Mockito.when(mongoTemplate.findAll(User.class)).thenReturn(allUsers);

        User userByID = new User("Vas", "pwd");
        userByID.setId("123456");
        Mockito.when(mongoTemplate.findById("123456", User.class)).thenReturn(userByID);

        return mongoTemplate;
    }
}
