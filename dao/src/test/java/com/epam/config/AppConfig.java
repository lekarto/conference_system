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
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User("2323", "22"));
        allUsers.add(new User("2323derfvb", "22234"));
        Mockito.when(mongoTemplate.findAll(User.class)).thenReturn(allUsers);

        Mockito.doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((User) args[0]).setId("2342");
            return null;
        }).doNothing().when(mongoTemplate).insert(User.class);

        return mongoTemplate;
    }
}
