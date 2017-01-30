package com.klishgroup.model.component;

import com.klishgroup.model.CallToAction;
import com.klishgroup.model.CountrySite;
import com.klishgroup.model.Link;
import com.klishgroup.targetting.Taggable;
import com.klishgroup.viewmodel.AbstractHeaderViewModel;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StorageItem;

import java.util.ArrayList;
import java.util.List;

@ViewBinding(value = AbstractHeaderViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
@Recordable.Abstract
public abstract class AbstractHeader extends Content implements Taggable {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    @Required
    private StorageItem logo;

    private List<Link> targetNavigation;

    private List<Link> otherNavigation;

    @ToolUi.Tab("Country Sites")
    private List<CountrySite> countrySites;

    @Required
    private CallToAction callToAction;

    @Required
    private Link memberLogin;

    @Required
    private Link searchPage;

    public CallToAction getCallToAction() {
        return callToAction;
    }

    public void setCallToAction(CallToAction callToAction) {
        this.callToAction = callToAction;
    }

    public Link getMemberLogin() {
        return memberLogin;
    }

    public void setMemberLogin(Link memberLogin) {
        this.memberLogin = memberLogin;
    }

    public Link getSearchPage() {
        return searchPage;
    }

    public void setSearchPage(Link searchPage) {
        this.searchPage = searchPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageItem getLogo() {
        return logo;
    }

    public void setLogo(StorageItem logo) {
        this.logo = logo;
    }

    public List<Link> getTargetNavigation() {
        if (ObjectUtils.isBlank(targetNavigation)) {
            return new ArrayList<>();
        }
        return targetNavigation;
    }

    public void setTargetNavigation(List<Link> targetNavigation) {
        this.targetNavigation = targetNavigation;
    }

    public List<Link> getOtherNavigation() {
        if (ObjectUtils.isBlank(otherNavigation)) {
            return new ArrayList<>();
        }
        return otherNavigation;
    }

    public void setOtherNavigation(List<Link> otherNavigation) {
        this.otherNavigation = otherNavigation;
    }

    public List<CountrySite> getCountrySites() {
        if (ObjectUtils.isBlank(countrySites)) {
            return new ArrayList<>();
        }
        return countrySites;
    }

    public void setCountrySites(List<CountrySite> countrySites) {
        this.countrySites = countrySites;
    }
}
