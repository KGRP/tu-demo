package com.klishgroup.model;

import com.psddev.cms.db.Seo;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.StorageItem;

@Seo.TitleFields({"name"})
public abstract class Resource extends Record {

    @Required
    @ToolUi.Note("CMS Only name")
    private String name;

    @Required
    private StorageItem item;

    public StorageItem getItem() {
        return item;
    }

    public void setItem(StorageItem item) {
        this.item = item;
    }

    public static class CssResource extends Resource {
    }

    public static class JsResource extends Resource {
    }
}

