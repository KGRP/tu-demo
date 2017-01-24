package com.klishgroup.model;

import com.klishgroup.model.page.AbstractPage;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class TitledPageList extends Record {

    @Required
    private String title;
    @Required
    @Minimum(1)
    private List<AbstractPage> pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AbstractPage> getPages() {
        if (ObjectUtils.isBlank(pages)) {
            return new ArrayList<>();
        }
        return pages;
    }

    public void setPages(List<AbstractPage> pages) {
        this.pages = pages;
    }
}
