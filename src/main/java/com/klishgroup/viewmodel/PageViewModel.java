package com.klishgroup.viewmodel;

import com.klishgroup.model.Resource;
import com.klishgroup.model.page.AbstractPage;
import com.klishgroup.view.base.page.ExternalScriptView;
import com.klishgroup.view.base.page.ExternalStylesheetView;
import com.klishgroup.view.base.page.HeadView;
import com.klishgroup.view.base.page.InlineScriptView;
import com.klishgroup.view.base.page.InlineStylesheetView;
import com.klishgroup.view.base.page.MetaView;
import com.klishgroup.view.base.util.ConcatenatedView;
import com.klishgroup.view.common.PageView;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.Seo;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PageViewModel extends AbstractViewModel<Content> implements PageView {

    @Override
    public HeadView getHead() {

        return new HeadView() {

            Seo.ObjectModification seoData = model.as(Seo.ObjectModification.class);

            @Override
            public String getTitle() {
                return !StringUtils.isBlank(seoData.getTitle()) ? seoData.getTitle() : "";
            }

            @Override
            public String getDescription() {
                return !StringUtils.isBlank(seoData.getDescription()) ? seoData.getDescription() : "";
            }

            @Override
            public String getKeywords() {
                LinkedHashSet<String> kwSet = new LinkedHashSet<>();

                Set<String> seoKeywords = model.as(Seo.ObjectModification.class).findKeywords();
                kwSet.addAll(seoKeywords != null ? seoKeywords : Collections.emptySet());

                return kwSet.stream()
                        .limit(10) // Google only takes the first 10
                        .collect(Collectors.joining(", "));
            }

            public List<Object> getMeta() {
                List<Object> metaList = new ArrayList<>();

                metaList.add(new MetaView.Builder()
                        .name("charset")
                        .content("utf-8")
                        .build());
                metaList.add(new MetaView.Builder()
                        .httpEquiv("X-UA-Compatible")
                        .content("IE=edge")
                        .build());
                metaList.add(new MetaView.Builder()
                        .name("viewport")
                        .content("width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no")
                        .build());

                return metaList;
            }

            @Override
            public List<Object> getItems() {
                List<Object> itemsList = new ArrayList<>();

                itemsList.addAll(getMeta());

                if (model instanceof AbstractPage) {
                    itemsList.addAll(createCssResourceView(((AbstractPage) model).getHeadResources()));
                }

                if (model instanceof AbstractPage) {
                    itemsList.addAll(createJsResourceView(((AbstractPage) model).getHeadResources()));
                }

                return itemsList;
            }

        };
    }

    @Override
    public Object getBody() {
        ConcatenatedView.Builder pageLayoutView = new ConcatenatedView.Builder();

        if (model instanceof AbstractPage) {

            AbstractPage page = (AbstractPage) model;

            page.getTargetedHeader().getModules(getRequest())
                    .stream()
                    .forEach(module -> pageLayoutView.addToItems(createView(AbstractViewModel.MODULE_VIEW_TYPE, module)));
            page.getModules()
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(module -> pageLayoutView.addToItems(createView(AbstractViewModel.MODULE_VIEW_TYPE, module)));
            pageLayoutView.addToItems(createView(AbstractViewModel.MODULE_VIEW_TYPE, page.getFooter()));
            pageLayoutView.addAllToItems(createJsResourceView(page.getBodyResources()));
            pageLayoutView.addToItems(new InlineStylesheetView.Builder()
                    .css(((AbstractPage) model).getExtraCss())
                    .build());
            pageLayoutView.addToItems(new InlineScriptView.Builder()
                    .script(((AbstractPage) model).getExtraJs())
                    .build());
        }

        return pageLayoutView.build();
    }

    private List<Object> createJsResourceView(List<Resource> resources) {
        List<Object> resourcesView = new ArrayList<>();

        if (!ObjectUtils.isBlank(resources)) {
            resources.stream()
                    .filter(Objects::nonNull)
                    .filter(resource -> resource instanceof Resource.JsResource)
                    .forEach(resource -> resourcesView.add(new ExternalScriptView.Builder()
                            .src(resource.getItem().getPublicUrl())
                            .build())
                    );
        }
        return resourcesView;
    }

    private List<Object> createCssResourceView(List<Resource> resources) {
        List<Object> resourcesView = new ArrayList<>();

        if (!ObjectUtils.isBlank(resources)) {
            resources.stream()
                    .filter(Objects::nonNull)
                    .filter(resource -> resource instanceof Resource.CssResource)
                    .forEach(resource -> resourcesView.add(new ExternalStylesheetView.Builder()
                            .href(resource.getItem().getPublicUrl())
                            .build())
                    );
        }
        return resourcesView;
    }
}
