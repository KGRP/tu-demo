package com.klishgroup.model;

import com.psddev.dari.db.Record;

public class Icon extends Record {

    @Required
    private String name;

    @Required
    private String cssClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}
