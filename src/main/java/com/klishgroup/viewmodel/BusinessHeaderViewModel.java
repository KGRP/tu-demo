package com.klishgroup.viewmodel;

import com.klishgroup.model.TitledPageList;
import com.klishgroup.model.component.BusinessHeader;
import com.klishgroup.model.page.AbstractPage;
import com.klishgroup.model.page.IndustryPage;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.component.BusinessHeaderMainNavView;
import com.klishgroup.view.component.MainNavNodeView;
import com.klishgroup.view.component.MainNavSubNavView;
import com.psddev.cms.db.Seo;
import com.psddev.dari.db.Query;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BusinessHeaderViewModel extends AbstractHeaderViewModel<BusinessHeader> {

    @Override
    public Object getMainNav() {

        BusinessHeaderMainNavView.Builder mainNav = new BusinessHeaderMainNavView.Builder();

        BusinessHeader.SolutionsAndProductsList solutionsAndProductsList = model.getSolutionsAndProducts();

        if (!ObjectUtils.isBlank(solutionsAndProductsList)) {

            List<TitledPageList> subNavs = solutionsAndProductsList.getSubNavs();

            if (!ObjectUtils.isBlank(subNavs) && subNavs.size() > 0) {

                TitledPageList active = subNavs.get(0);

                mainNav.solutionsAndProducts(new MainNavNodeView.Builder()
                        .title(solutionsAndProductsList.getLabel())
                        .id(com.psddev.dari.util.StringUtils.toCamelCase(solutionsAndProductsList.getLabel()))
                        .addAllToSubNavs(model.getSolutionsAndProducts().getSubNavs().stream()
                                .filter(Objects::nonNull)
                                .map(subNav -> new MainNavSubNavView.Builder()
                                        .title(subNav.getTitle())
                                        .id(com.psddev.dari.util.StringUtils.toCamelCase(subNav.getTitle()))
                                        .active((subNav.equals(active)))
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
            }
        }



        List<IndustryPage> industries = Query.from(IndustryPage.class).selectAll();
        if (!ObjectUtils.isBlank(industries)) {
            mainNav.industries(new MainNavNodeView.Builder()
                    .title(model.getIndustries().getLabel())
                    .id(StringUtils.remove(StringUtils.capitalize(model.getIndustries().getLabel()), " "))
                    .addAllToLinks(industries.stream()
                            .filter(Objects::nonNull)
                            .map(page -> new LinkView.Builder()
                                    .href(page.getPermalink())
                                    .body(page.getName())
                                    .description(page.as(Seo.ObjectModification.class).getDescription())
                                    .index(org.apache.commons.lang.StringUtils.remove(org.apache.commons.lang.StringUtils.capitalize(page.getName() + page.getId().toString()), " "))
                                    .id(org.apache.commons.lang.StringUtils.remove(org.apache.commons.lang.StringUtils.capitalize(page.getName()), " "))
                                    .build())
                            .collect(Collectors.toList()))
                    .build());
        }

        mainNav.insightsAndEvents(new MainNavNodeView.Builder()
                .title(model.getInsightsAndEvents().getLabel())
                .id(StringUtils.remove(StringUtils.capitalize(model.getInsightsAndEvents().getLabel()), " "))
                .content(createView(HeaderInsightsAndEventsViewModel.class, model.getInsightsAndEvents().getLinks()))
                .build());

        return mainNav.build();
    }
}
