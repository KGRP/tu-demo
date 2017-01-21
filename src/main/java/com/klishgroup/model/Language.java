package com.klishgroup.model;

import com.psddev.dari.db.Record;

public class Language extends Record {

    @Required
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
