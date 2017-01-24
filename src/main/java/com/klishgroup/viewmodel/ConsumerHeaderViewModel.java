package com.klishgroup.viewmodel;

import com.klishgroup.model.SiteMap;
import com.klishgroup.model.component.ConsumerHeader;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.component.ConsumerHeaderMainNavView;
import com.klishgroup.view.component.MainNavNodeView;
import com.psddev.cms.db.Seo;
import com.psddev.dari.db.Query;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

public class ConsumerHeaderViewModel extends AbstractHeaderViewModel<ConsumerHeader> {

    @Override
    public Object getMainNav() {

        SiteMap siteMap = Query.from(SiteMap.class).first();
        if (ObjectUtils.isBlank(siteMap)) {
            return null;
        }

        ConsumerHeaderMainNavView.Builder mainNav = new ConsumerHeaderMainNavView.Builder();

        siteMap.getNodes().stream()
                .filter(Objects::nonNull)
                .forEach(node -> mainNav.addToNodes(new MainNavNodeView.Builder()
                        .title(node.getName())
                        .id(StringUtils.remove(StringUtils.capitalize(node.getName()), " "))
                        .addAllToLinks(node.getPages().stream()
                                .filter(Objects::nonNull)
                                .map(page -> new LinkView.Builder()
                                        .href(page.getPermalink())
                                        .body(page.getName())
                                        .description(page.as(Seo.ObjectModification.class).getDescription())
                                        .index(StringUtils.remove(StringUtils.capitalize(node.getName() + page.getId().toString()), " "))
                                        .id(StringUtils.remove(StringUtils.capitalize(page.getName()), " "))
                                        .build())
                                .collect(Collectors.toList()))
                        .build()));

        return mainNav.build();
    }
}
