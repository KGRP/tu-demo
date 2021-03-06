package com.klishgroup.model;

import com.klishgroup.model.component.BannerCarouselSlide;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
public class CallToAction extends Record {

    @Required
    @ToolUi.RichText
    private String desktopLabel;

    @Required
    private BannerCarouselSlide.CTAButtonType desktopButtonType;

    @Required
    @ToolUi.RichText
    private String mobileLabel;

    @Required
    private BannerCarouselSlide.CTAButtonType mobileButtonType;

    @Required
    private Link link;

    public String getDesktopLabel() {
        return desktopLabel;
    }

    public void setDesktopLabel(String desktopLabel) {
        this.desktopLabel = desktopLabel;
    }

    public String getMobileLabel() {
        return mobileLabel;
    }

    public void setMobileLabel(String mobileLabel) {
        this.mobileLabel = mobileLabel;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public BannerCarouselSlide.CTAButtonType getDesktopButtonType() {
        return desktopButtonType;
    }

    public void setDesktopButtonType(BannerCarouselSlide.CTAButtonType desktopButtonType) {
        this.desktopButtonType = desktopButtonType;
    }

    public BannerCarouselSlide.CTAButtonType getMobileButtonType() {
        return mobileButtonType;
    }

    public void setMobileButtonType(BannerCarouselSlide.CTAButtonType mobileButtonType) {
        this.mobileButtonType = mobileButtonType;
    }
}
