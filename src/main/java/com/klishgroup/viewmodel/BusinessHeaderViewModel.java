package com.klishgroup.viewmodel;

import com.klishgroup.model.component.BusinessHeader;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.component.BusinessHeaderMainNavView;
import com.klishgroup.view.component.MainNavNodeView;
import com.klishgroup.view.component.MainNavSubNavView;
import com.psddev.cms.db.Seo;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

public class BusinessHeaderViewModel extends AbstractHeaderViewModel<BusinessHeader> {

    @Override
    public Object getMainNav() {

        BusinessHeaderMainNavView.Builder mainNav = new BusinessHeaderMainNavView.Builder();

        mainNav.solutionsAndProducts(new MainNavNodeView.Builder()
                .title(model.getSollutionsAndProducts().getLabel())
                .id(StringUtils.remove(StringUtils.capitalize(model.getSollutionsAndProducts().getLabel()), " "))
                .addAllToSubNavs(model.getSollutionsAndProducts().getSubNavs().stream()
                        .filter(Objects::nonNull)
                        .map(subNav -> new MainNavSubNavView.Builder()
                                .title(subNav.getTitle())
                                .id(StringUtils.remove(StringUtils.capitalize(subNav.getTitle()), " "))
                                .addAllToLinks(subNav.getPages().stream()
                                        .filter(Objects::nonNull)
                                        .map(page -> new LinkView.Builder()
                                                .href(page.getPermalink())
                                                .body(page.getName())
                                                .description(page.as(Seo.ObjectModification.class).getDescription())
                                                .index(StringUtils.remove(StringUtils.capitalize(subNav.getTitle() + page.getId().toString()), " "))
                                                .id(StringUtils.remove(StringUtils.capitalize(page.getName()), " "))
                                                .build())
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList()))
                                .build())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .build());

        mainNav.industries(new MainNavNodeView.Builder()
                .title(model.getIndustries().getLabel())
                .id(StringUtils.remove(StringUtils.capitalize(model.getIndustries().getLabel()), " "))
                .build());

        mainNav.insightsAndEvents(new MainNavNodeView.Builder()
                .title(model.getInsightsAndEvents().getLabel())
                .id(StringUtils.remove(StringUtils.capitalize(model.getInsightsAndEvents().getLabel()), " "))
                .content(createView(AbstractViewModel.MODULE_VIEW_TYPE,
                        model.getInsightsAndEvents().getLinks()))
                .build());

        return mainNav.build();
    }
}
