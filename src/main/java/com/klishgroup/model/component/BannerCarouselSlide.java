package com.klishgroup.model.component;

import com.klishgroup.model.Link;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.StorageItem;

@Recordable.Embedded
@Recordable.LabelFields({"title"})
public class BannerCarouselSlide extends Record {

    private String eyebrowTitle;

    @Recordable.Required
    private String title;

    @Recordable.Required
    private String body;

    @Recordable.Required
    private Link ctaButton;

    private StorageItem desktopImage;

    private StorageItem mobileImage;

    public String getEyebrowTitle() {
        return eyebrowTitle;
    }

    public void setEyebrowTitle(String eyebrowTitle) {
        this.eyebrowTitle = eyebrowTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Link getCtaButton() {
        return ctaButton;
    }

    public void setCtaButton(Link ctaButton) {
        this.ctaButton = ctaButton;
    }

    public StorageItem getDesktopImage() {
        return desktopImage;
    }

    public void setDesktopImage(StorageItem desktopImage) {
        this.desktopImage = desktopImage;
    }

    public StorageItem getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(StorageItem mobileImage) {
        this.mobileImage = mobileImage;
    }
}
