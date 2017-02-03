package com.klishgroup.model.component;

import com.klishgroup.model.TitledPageList;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.BusinessHeaderViewModel;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@ViewBinding(value = BusinessHeaderViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class BusinessHeader extends AbstractHeader {

    @Required
    @Embedded
    private SolutionsAndProductsList solutionsAndProducts;

    @Required
    @Embedded
    private IndustriesList industries;

    @Required
    @Embedded
    private InsightsAndEventsList insightsAndEvents;

    public SolutionsAndProductsList getSolutionsAndProducts() {
        return solutionsAndProducts;
    }

    public void setSolutionsAndProducts(SolutionsAndProductsList solutionsAndProducts) {
        this.solutionsAndProducts = solutionsAndProducts;
    }

    public IndustriesList getIndustries() {
        return industries;
    }

    public void setIndustries(IndustriesList industries) {
        this.industries = industries;
    }

    public InsightsAndEventsList getInsightsAndEvents() {
        return insightsAndEvents;
    }

    public void setInsightsAndEvents(InsightsAndEventsList insightsAndEvents) {
        this.insightsAndEvents = insightsAndEvents;
    }

    public static abstract class MainNavLinkList extends Record {

        @Required
        private String label;

        @Override
        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class SolutionsAndProductsList extends MainNavLinkList {

        @Required
        @Minimum(1)
        private List<TitledPageList> subNavs;

        public List<TitledPageList> getSubNavs() {
            if (ObjectUtils.isBlank(subNavs)) {
                return new ArrayList<>();
            }
            return subNavs;
        }

        public void setSubNavs(List<TitledPageList> subNavs) {
            this.subNavs = subNavs;
        }
    }

    public static class IndustriesList extends MainNavLinkList {

        @ToolUi.ReadOnly
        private String links = "To be generated dynamically using Industires content type";

        public String getLinks() {
            return links;
        }

        public void setLinks(String links) {
            this.links = links;
        }
    }

    public static class InsightsAndEventsList extends MainNavLinkList {

        @Required
        private InsightsAndEvents links;

        public InsightsAndEvents getLinks() {
            return links;
        }

        public void setLinks(InsightsAndEvents links) {
            this.links = links;
        }
    }
}
