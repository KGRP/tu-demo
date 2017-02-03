package com.klishgroup.model;

import com.klishgroup.targetting.Taggable;
import com.psddev.cms.db.Content;
import com.psddev.dari.util.StorageItem;

@Content.PreviewField("file")
public class Image extends Content implements Taggable {

    @Required
    private String name;

    @Required
    private StorageItem file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageItem getFile() {
        return file;
    }

    public void setFile(StorageItem file) {
        this.file = file;
    }
}