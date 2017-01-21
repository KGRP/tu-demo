package com.klishgroup.model.component;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;

public class RawContent extends Record {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    @Required
    @ToolUi.RichText
    String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
