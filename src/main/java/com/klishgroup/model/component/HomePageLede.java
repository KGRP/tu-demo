package com.klishgroup.model.component;

import com.klishgroup.model.Link;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.HomePageLedeViewModel;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.util.StorageItem;

@ViewBinding(value = HomePageLedeViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class HomePageLede extends Module {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    @Required
    private String headline1;

    @Required
    private String headline2;

    @Required
    private String headline3;

    @Required
    private Link ctaButton;

    @ToolUi.RichText
    private String alreadyMemberLink;

    private StorageItem image;

    private StorageItem imageMobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadline1() {
        return headline1;
    }

    public void setHeadline1(String headline1) {
        this.headline1 = headline1;
    }

    public String getHeadline2() {
        return headline2;
    }

    public void setHeadline2(String headline2) {
        this.headline2 = headline2;
    }

    public String getHeadline3() {
        return headline3;
    }

    public void setHeadline3(String headline3) {
        this.headline3 = headline3;
    }

    public Link getCtaButton() {
        return ctaButton;
    }

    public void setCtaButton(Link ctaButton) {
        this.ctaButton = ctaButton;
    }

    public String getAlreadyMemberLink() {
        return alreadyMemberLink;
    }

    public void setAlreadyMemberLink(String alreadyMemberLink) {
        this.alreadyMemberLink = alreadyMemberLink;
    }

    public StorageItem getImage() {
        return image;
    }

    public void setImage(StorageItem image) {
        this.image = image;
    }

    public StorageItem getImageMobile() {
        return imageMobile;
    }

    public void setImageMobile(StorageItem imageMobile) {
        this.imageMobile = imageMobile;
    }
}
