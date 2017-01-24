package com.klishgroup.viewmodel;

import com.klishgroup.model.InsightOrEvent;
import com.klishgroup.model.Link;
import com.klishgroup.model.component.InsightsAndEvents;
import com.klishgroup.view.base.util.ConcatenatedView;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.component.InsightsAndEventsView;
import com.psddev.dari.db.Query;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InsightsAndEventsViewModel extends AbstractViewModel<InsightsAndEvents> implements InsightsAndEventsView {

    @Override
    public Object getTitle() {
        return model.getTitle();
    }

    @Override
    public Object getViewAll() {

        Link viewAllLink = model.getViewAll();

        return new LinkView.Builder()
                .href(viewAllLink.getHref())
                .body(viewAllLink.getText())
                .build();
    }

    @Override
    public Object getContent() {

        List<InsightOrEvent> items = Query.from(InsightOrEvent.class).sortDescending("cms.content.publishDate").selectAll();

        if (ObjectUtils.isBlank(items)) {
            if (ObjectUtils.isBlank(model.getDefaultInsightOrEvent())) {
                return null;
            }
            items = new ArrayList<>();
            items.add(model.getDefaultInsightOrEvent());
        }
        return new ConcatenatedView.Builder()
                .addAllToItems(items.stream()
                                    .filter(Objects::nonNull)
                                    .map(item -> createView(AbstractViewModel.MODULE_VIEW_TYPE, item))
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList()))
                .build();
    }

}
