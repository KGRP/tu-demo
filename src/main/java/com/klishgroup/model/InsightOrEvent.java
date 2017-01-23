package com.klishgroup.model;

import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.InsightOrEventViewModel;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.StorageItem;

@ViewBinding(value = InsightOrEventViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
@Recordable.LabelFields({"name"})
public class InsightOrEvent extends Content {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    private String eyebrowTitle;

    private String description;

    @Required
    private StorageItem thumbnail;

    @Required
    private Link cta;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEyebrowTitle() {
        return eyebrowTitle;
    }

    public void setEyebrowTitle(String eyebrowTitle) {
        this.eyebrowTitle = eyebrowTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StorageItem getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(StorageItem thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Link getCta() {
        return cta;
    }

    public void setCta(Link cta) {
        this.cta = cta;
    }
}
