package com.epam.rest;

import com.epam.model.User;
import com.epam.service.UserService;
import com.epam.status.UpdateStatus;
import com.epam.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserRest {
    @Autowired
    private UserService userService;

    @Autowired
    private Logger LOGGER;

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(UserView.Summary.class)
    @ResponseBody
    public List<User> getAll() {
        LOGGER.debug("UserRest.getUsers()");
        return userService.getList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(UserView.Summary.class)
    @ResponseBody
    public User get(@PathVariable String id) {
        LOGGER.debug("UserRest.getUsers(): id= "+id);
        return userService.getById(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getCount() {
        LOGGER.debug("UserRest.getCount()");
        return userService.getTotalCount();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    @ResponseBody
    public User getByName(@RequestParam("name") String name) {
        LOGGER.debug("UserRest.getByName() name: "+name);
        return userService.getByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"email"})
    @ResponseBody
    public User getByEmail(@RequestParam("email") String email) {
        LOGGER.debug("UserRest.getByEmail() name: "+email);
        return userService.getByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<User> create(@RequestBody User user) {
        LOGGER.debug("UserRest.create: "+user);
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id) {
        LOGGER.debug("UserRest.delete: "+id);
        return new ResponseEntity<>(userService.delete(id)? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable(value = "id") String id, @RequestBody User user) {
        LOGGER.debug("UserRest.update: "+id);
        user.setId(id);
        HttpStatus responseStatus;
        UpdateStatus status = userService.update(user);
        switch (status) {
            case UPDATED:   { responseStatus = HttpStatus.NO_CONTENT; break; }
            case NOT_FOUND: { responseStatus = HttpStatus.NOT_FOUND; break; }
            default:        { responseStatus = HttpStatus.INTERNAL_SERVER_ERROR; break; }
        }
        return new ResponseEntity<>(responseStatus);
    }
}