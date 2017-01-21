package com.klishgroup.model;

import com.psddev.dari.db.Record;

public class CountrySite extends Record {

    @Required
    private String countryName;

    @Required
    private Icon flagIcon;

    private Language language;

    private Link link;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Icon getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(Icon flagIcon) {
        this.flagIcon = flagIcon;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
