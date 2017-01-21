package com.klishgroup.model;

import com.klishgroup.model.page.AbstractPage;
import com.psddev.cms.db.Content;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class SiteMap extends Content {

    @Required
    private String name;

    @Embedded
    private List<Node> nodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodes() {
        if (ObjectUtils.isBlank(nodes)) {
            return new ArrayList<>();
        }
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public static class Node extends Record {

        @Required
        private String name;

        @Required
        private List<AbstractPage> pages;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AbstractPage> getPages() {
            if (ObjectUtils.isBlank(pages)) {
                return new ArrayList<>();
            }
            return pages;
        }

        public void setPages(List<AbstractPage> pages) {
            this.pages = pages;
        }
    }
}
