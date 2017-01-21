package com.klishgroup.viewmodel;

import com.klishgroup.model.SiteMap;
import com.klishgroup.model.component.ConsumerHeader;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.base.util.RawHtmlView;
import com.klishgroup.view.common.ConsumerHeaderView;
import com.klishgroup.view.component.ConsumerHeaderCallToActionView;
import com.klishgroup.view.component.ConsumerHeaderMainNavView;
import com.klishgroup.view.component.LanguageNavView;
import com.klishgroup.view.component.MainNavNodeView;
import com.klishgroup.view.component.TargetNavView;
import com.klishgroup.view.component.UtilityNavView;
import com.psddev.cms.db.Seo;
import com.psddev.cms.view.ViewResponse;
import com.psddev.dari.db.Query;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

public class ConsumerHeaderViewModel extends AbstractViewModel<ConsumerHeader> implements ConsumerHeaderView {

    private String href;

    @Override
    protected void onCreate(ViewResponse response) {
        super.onCreate(response);

        href = (model.getTargetNavigation().size() > 1) ? model.getTargetNavigation().get(0).getHref() : null;
    }

    @Override
    public Object getLogoUrl() {
        return model.getLogo().getPublicUrl();
    }

    @Override
    public Object getLogoLink() {
        return new LinkView.Builder()
                .href((StringUtils.isBlank(href)) ? "#" : href)
                .build();
    }

    @Override
    public Object getTargetNav() {

        TargetNavView.Builder targetNavView = new TargetNavView.Builder();

        model.getTargetNavigation()
                .stream()
                .filter(Objects::nonNull)
                .forEach(link -> targetNavView.addToNavLinks(new LinkView.Builder()
                        .href(link.getHref())
                        .body(link.getText())
                        .addExtraAttributes("active", (link.getHref().equals(href)))
                        .build()));

        targetNavView.searchURL(model.getSearchPage().getHref());
        targetNavView.cancelLabel("Cancel");

        return targetNavView.build();
    }

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
                                        .index(page.getId().toString())
                                        .id(StringUtils.remove(StringUtils.capitalize(page.getName()), " "))
                                        .build())
                                .collect(Collectors.toList()))
                        .build()));

        return mainNav.build();
    }

    @Override
    public Object getUtilityNav() {

        UtilityNavView.Builder utilityNavView = new UtilityNavView.Builder();

        model.getOtherNavigation()
                .stream()
                .filter(Objects::nonNull)
                .forEach(link -> utilityNavView.addToNavLinks(new LinkView.Builder()
                        .href(link.getHref())
                        .body(link.getText())
                        .build()));

        utilityNavView.memberLoginLink(new LinkView.Builder()
                .href(model.getMemberLogin().getHref())
                .body(model.getMemberLogin().getText())
                .build());
        utilityNavView.searchURL(model.getSearchPage().getHref());
        utilityNavView.languageNav(new LanguageNavView.Builder()
                .addAllToCountries(model.getCountrySites()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(countrySite -> new LinkView.Builder()
                                .href(countrySite.getLink().getHref())
                                .body(countrySite.getCountryName())
                                .addExtraAttributes("class", countrySite.getFlagIcon().getCssClass())
                                .addExtraAttributes("language", countrySite.getLanguage().getName())
                                .build())
                        .collect(Collectors.toList()))
                .build());

        return utilityNavView.build();
    }

    @Override
    public Object getCallToAction() {
        return new ConsumerHeaderCallToActionView.Builder()
                .desktopLabel(new RawHtmlView.Builder()
                        .html(model.getCallToAction().getDesktopLabel())
                        .build())
                .mobileLabel(new RawHtmlView.Builder()
                        .html(model.getCallToAction().getMobileLabel())
                        .build())
                .link(new LinkView.Builder()
                        .href(model.getCallToAction().getLink().getHref())
                        .build())
                .build();
    }
}
