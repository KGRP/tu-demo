package com.klishgroup.model;

import com.klishgroup.targetting.Targeted;
import com.psddev.dari.db.Record;

public class TitledPageList extends Record {

    @Required
    private String title;
    @Embedded
    @Required
    private Targeted.MultipleContent pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Targeted.MultipleContent getPages() {
        return pages;
    }

    public void setPages(Targeted.MultipleContent pages) {
        this.pages = pages;
    }
}
