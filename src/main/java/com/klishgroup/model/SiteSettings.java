package com.klishgroup.model;

import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Modification;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class SiteSettings extends Modification<Site> {

    @ToolUi.Heading("Site wide JS/CSS Resources")
    private List<Resource> headResources;

    @ToolUi.Note("Only JS allowed in body")
    @Types({Resource.JsResource.class})
    private List<Resource> bodyResources;

    public List<Resource> getHeadResources() {
        if (ObjectUtils.isBlank(headResources)) {
            return new ArrayList<>();
        }
        return headResources;
    }

    public void setHeadResources(List<Resource> headResources) {
        this.headResources = headResources;
    }

    public List<Resource> getBodyResources() {
        if (ObjectUtils.isBlank(bodyResources)) {
            return new ArrayList<>();
        }
        return bodyResources;
    }

    public void setBodyResources(List<Resource> bodyResources) {
        this.bodyResources = bodyResources;
    }
}
