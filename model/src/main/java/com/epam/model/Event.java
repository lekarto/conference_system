package com.epam.model;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

public class Event {
    @JsonView
    private Integer id;
    @JsonView
    private String name;
    @JsonView
    private Date date;

    public Event() { }

    public Event(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
