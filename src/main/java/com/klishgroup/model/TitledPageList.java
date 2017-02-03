package com.klishgroup.model;

import com.klishgroup.model.page.BusinessPage;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class TitledPageList extends Record {

    @Required
    private String title;

    @ToolUi.DropDown
    @Required
    private List<BusinessPage> pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BusinessPage> getPages() {
        if (ObjectUtils.isBlank(pages)) {
            return new ArrayList<>();
        }
        return pages;
    }

    public void setPages(List<BusinessPage> pages) {
        this.pages = pages;
    }
}
