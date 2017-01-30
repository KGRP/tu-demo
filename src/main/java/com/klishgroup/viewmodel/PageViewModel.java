package com.klishgroup.viewmodel;

import com.klishgroup.model.Image;
import com.klishgroup.model.Meta;
import com.klishgroup.model.Resource;
import com.klishgroup.model.SiteSettings;
import com.klishgroup.model.component.AbstractHeader;
import com.klishgroup.model.component.Footer;
import com.klishgroup.model.page.AbstractPage;
import com.klishgroup.model.page.BusinessPage;
import com.klishgroup.model.page.ConsumerPage;
import com.klishgroup.view.base.page.ExternalScriptView;
import com.klishgroup.view.base.page.ExternalStylesheetView;
import com.klishgroup.view.base.page.HeadView;
import com.klishgroup.view.base.page.InlineScriptView;
import com.klishgroup.view.base.page.InlineStylesheetView;
import com.klishgroup.view.base.page.MetaView;
import com.klishgroup.view.base.util.ConcatenatedView;
import com.klishgroup.view.common.FaviconIconView;
import com.klishgroup.view.common.PageView;
import com.psddev.cms.db.Seo;
import com.psddev.cms.db.Site;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PageViewModel extends AbstractViewModel<AbstractPage> implements PageView {

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

                Site site = getSite();
                if (!ObjectUtils.isBlank(site)) {
                    site.as(SiteSettings.class).getMetas().stream()
                            .filter(Objects::nonNull)
                            .forEach(meta -> {
                                MetaView.Builder view = new MetaView.Builder();

                                view.content(meta.getContent());

                                Meta.MetaType type = meta.getType();
                                Meta.MetaTypeAttribute attribute = type.getAttribute();

                                if (attribute.equals(Meta.MetaTypeAttribute.HTTP_EQUIV)) {
                                    view.httpEquiv(type.getAttributeValue());
                                } else if (attribute.equals(Meta.MetaTypeAttribute.NAME)) {
                                    view.name(type.getAttributeValue());
                                } else if (attribute.equals(Meta.MetaTypeAttribute.PROPERTY)) {
                                    view.property(type.getAttributeValue());
                                }

                                metaList.add(view.build());
                            });
                }

                return metaList;
            }

            @Override
            public List<Object> getItems() {
                List<Object> itemsList = new ArrayList<>();

                itemsList.addAll(getMeta());

                if (ObjectUtils.isBlank(getSite())) {
                    return itemsList;
                }

                itemsList.addAll(createCssResourceView(getSite().as(SiteSettings.class).getHeadResources()));
                itemsList.add(createFaviconIcon(getSite().as(SiteSettings.class).getFaviconIcon()));
                itemsList.addAll(createCssResourceView(((AbstractPage) model).getHeadResources()));

                itemsList.addAll(createJsResourceView(getSite().as(SiteSettings.class).getHeadResources()));
                itemsList.addAll(createJsResourceView(((AbstractPage) model).getHeadResources()));

                return itemsList;
            }

        };
    }

    @Override
    public Object getBody() {
        ConcatenatedView.Builder pageLayoutView = new ConcatenatedView.Builder();


        AbstractPage page = model;

        pageLayoutView.addToItems(createHeaderView(model));

        page.getModules()
                .stream()
                .filter(Objects::nonNull)
                .forEach(module -> pageLayoutView.addToItems(createView(AbstractViewModel.MODULE_VIEW_TYPE, module)));

        pageLayoutView.addToItems(createFooterView(model));

        if (!ObjectUtils.isBlank(getSite())) {
            pageLayoutView.addAllToItems(createJsResourceView(getSite().as(SiteSettings.class).getBodyResources()));
        }

        pageLayoutView.addAllToItems(createJsResourceView(page.getBodyResources()));

        pageLayoutView.addToItems(new InlineStylesheetView.Builder()
                .css((model).getExtraCss())
                .build());

        pageLayoutView.addToItems(new InlineScriptView.Builder()
                .script((model).getExtraJs())
                .build());

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

    private Object createFaviconIcon(Image icon) {

        if (ObjectUtils.isBlank(icon)) {
            return null;
        }
        return new FaviconIconView.Builder()
                .href(icon.getFile().getPublicUrl())
                .build();
    }

    private Object createHeaderView(AbstractPage page) {

        AbstractHeader header = page.getHeader();
        if (ObjectUtils.isBlank(header)) {

            Site site = getSite();
            if (!ObjectUtils.isBlank(site)) {

                if (page instanceof ConsumerPage) {
                    header = site.as(SiteSettings.class).getConsumerHeader();
                } else if (page instanceof BusinessPage) {
                    header = site.as(SiteSettings.class).getBusinessHeader();
                }
            }
        }
        return createView(AbstractViewModel.MODULE_VIEW_TYPE, header);
    }

    private Object createFooterView(AbstractPage page) {

        Footer footer = page.getFooter();
        if (ObjectUtils.isBlank(footer)) {

            Site site = getSite();
            if (!ObjectUtils.isBlank(site)) {
                footer = site.as(SiteSettings.class).getGlobalFooter();
            }
        }
        return createView(AbstractViewModel.MODULE_VIEW_TYPE, footer);
    }
}
