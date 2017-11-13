package com.dot.pojo;

import java.util.Date;

public class TbSensorData extends TbSensorDataKey {
    private Date date;

    private Long timeseq;

    private Float value;

    private Float value1;

    private Float value2;

    private Float value3;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTimeseq() {
        return timeseq;
    }

    public void setTimeseq(Long timeseq) {
        this.timeseq = timeseq;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getValue1() {
        return value1;
    }

    public void setValue1(Float value1) {
        this.value1 = value1;
    }

    public Float getValue2() {
        return value2;
    }

    public void setValue2(Float value2) {
        this.value2 = value2;
    }

    public Float getValue3() {
        return value3;
    }

    public void setValue3(Float value3) {
        this.value3 = value3;
    }
}