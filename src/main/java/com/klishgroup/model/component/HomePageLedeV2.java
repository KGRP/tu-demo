package com.klishgroup.model.component;

import com.klishgroup.model.Link;
import com.klishgroup.targeted.TargetedImage;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.HomePageLedeV2ViewModel;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;

@ViewBinding(value = HomePageLedeV2ViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class HomePageLedeV2 extends Content implements Module {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    @ToolUi.RichText
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

    private TargetedImage image;

    private TargetedImage imageMobile;

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

    public TargetedImage getImage() {
        return image;
    }

    public void setImage(TargetedImage image) {
        this.image = image;
    }

    public TargetedImage getImageMobile() {
        return imageMobile;
    }

    public void setImageMobile(TargetedImage imageMobile) {
        this.imageMobile = imageMobile;
    }
}
