package com.epam.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Auditorium {
    @JsonView
    private Integer id;
    @JsonView
    private Integer seatsNum;
    @JsonView
    private String vipSeatsNums;

    public Auditorium() { }

    public Auditorium(Integer seatsNum, String vipSeatsNums) {
        this.seatsNum = seatsNum;
        this.vipSeatsNums = vipSeatsNums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(Integer seatsNum) {
        this.seatsNum = seatsNum;
    }

    public String getVipSeatsNums() {
        return vipSeatsNums;
    }

    public void setVipSeatsNums(String vipSeatsNums) {
        this.vipSeatsNums = vipSeatsNums;
    }
}
