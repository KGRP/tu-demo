package com.klishgroup.model.component;

import com.klishgroup.model.CountrySite;
import com.klishgroup.model.Link;
import com.klishgroup.targetting.Taggable;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StorageItem;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHeader extends Module implements Taggable {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    @Required
    private StorageItem logo;

    private List<Link> targetNavigation;

    private List<Link> otherNavigation;

    @ToolUi.Tab("Country Sites")
    private List<CountrySite> countrySites;

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
