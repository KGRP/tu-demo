package com.klishgroup.model.component;

import com.klishgroup.model.Link;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.FooterViewModel;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@ViewBinding(value = FooterViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class Footer extends Module {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    private List<TitledLinkList> linkSections;

    private List<Link> miscellaneousLinks;

    @Required
    private CTA cta;

    private List<Link> socialLinks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TitledLinkList> getLinkSections() {
        if (ObjectUtils.isBlank(linkSections)) {
            return new ArrayList<>();
        }
        return linkSections;
    }

    public void setLinkSections(List<TitledLinkList> linkSections) {
        this.linkSections = linkSections;
    }

    public List<Link> getMiscellaneousLinks() {
        if (ObjectUtils.isBlank(miscellaneousLinks)) {
            return new ArrayList<>();
        }
        return miscellaneousLinks;
    }

    public void setMiscellaneousLinks(List<Link> miscellaneousLinks) {
        this.miscellaneousLinks = miscellaneousLinks;
    }

    public CTA getCta() {
        return cta;
    }

    public void setCta(CTA cta) {
        this.cta = cta;
    }

    public List<Link> getSocialLinks() {
        if (ObjectUtils.isBlank(socialLinks)) {
            return new ArrayList<>();
        }
        return socialLinks;
    }

    public void setSocialLinks(List<Link> socialLinks) {
        this.socialLinks = socialLinks;
    }

    @Embedded
    public static class TitledLinkList extends Record {

        @Required
        private String title;
        @Required
        @Minimum(1)
        private List<Link> links;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Link> getLinks() {
            if (ObjectUtils.isBlank(links)) {
                return new ArrayList<>();
            }
            return links;
        }

        public void setLinks(List<Link> links) {
            this.links = links;
        }
    }

    @Embedded
    public static class CTA extends Record {

        @Required
        private String title;

        @Required
        private Link link;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Link getLink() {
            return link;
        }

        public void setLink(Link link) {
            this.link = link;
        }
    }
}
