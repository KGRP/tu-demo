package com.klishgroup.model;

import com.klishgroup.model.component.BusinessHeader;
import com.klishgroup.model.component.ConsumerHeader;
import com.klishgroup.model.component.Footer;
import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Modification;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StorageItem;

import java.util.ArrayList;
import java.util.List;

public class SiteSettings extends Modification<Site> {

    @ToolUi.Heading("Site wide Headers/Footer")
    private ConsumerHeader consumerHeader;

    private BusinessHeader businessHeader;

    private Footer globalFooter;

    @ToolUi.Heading("Site wide JS/CSS Resources")
    private List<Resource> headResources;

    @ToolUi.Note("Only JS allowed in body")
    @Types({Resource.JsResource.class})
    private List<Resource> bodyResources;

    private Image faviconIcon;

    private List<Meta> metas;

    public ConsumerHeader getConsumerHeader() {
        return consumerHeader;
    }

    public void setConsumerHeader(ConsumerHeader consumerHeader) {
        this.consumerHeader = consumerHeader;
    }

    public BusinessHeader getBusinessHeader() {
        return businessHeader;
    }

    public void setBusinessHeader(BusinessHeader businessHeader) {
        this.businessHeader = businessHeader;
    }

    public Footer getGlobalFooter() {
        return globalFooter;
    }

    public void setGlobalFooter(Footer globalFooter) {
        this.globalFooter = globalFooter;
    }

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

    public Image getFaviconIcon() {
        return faviconIcon;
    }

    public void setFaviconIcon(Image faviconIcon) {
        this.faviconIcon = faviconIcon;
    }

    public List<Meta> getMetas() {
        if (ObjectUtils.isBlank(metas)) {
            return new ArrayList<>();
        }
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }
}
