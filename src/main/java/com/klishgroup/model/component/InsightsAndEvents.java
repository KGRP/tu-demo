package com.klishgroup.model.component;

import com.klishgroup.model.Link;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.InsightsAndEventsViewModel;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;

@ViewBinding(value = InsightsAndEventsViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class InsightsAndEvents extends Module {

    @Required
    @ToolUi.Note("CMS Only")
    private String name;

    @Required
    private String title;

    @Required
    private Link viewAll;

    @ToolUi.ReadOnly
    private String insightsOrEvents = "Most recent 4 insights and events";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Link getViewAll() {
        return viewAll;
    }

    public void setViewAll(Link viewAll) {
        this.viewAll = viewAll;
    }

    public String getInsightsOrEvents() {
        return insightsOrEvents;
    }

    public void setInsightsOrEvents(String insightsOrEvents) {
        this.insightsOrEvents = insightsOrEvents;
    }
}
