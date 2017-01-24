package com.klishgroup.viewmodel;

import com.klishgroup.model.CountrySite;
import com.klishgroup.model.component.AbstractHeader;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.base.util.RawHtmlView;
import com.klishgroup.view.common.HeaderView;
import com.klishgroup.view.component.ConsumerHeaderCallToActionView;
import com.klishgroup.view.component.LanguageNavView;
import com.klishgroup.view.component.TargetNavView;
import com.klishgroup.view.component.UtilityNavView;
import com.psddev.cms.view.ViewResponse;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractHeaderViewModel<M extends AbstractHeader> extends AbstractViewModel<M> implements HeaderView {

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

        List<CountrySite> countrySiteList = model.getCountrySites();

        if (!ObjectUtils.isBlank(countrySiteList) && countrySiteList.size() > 0) {
            CountrySite defaultSite = countrySiteList.get(0);

            utilityNavView.languageNav(new LanguageNavView.Builder()
                    .addAllToCountries(model.getCountrySites()
                            .stream()
                            .filter(Objects::nonNull)
                            .map(countrySite -> new LinkView.Builder()
                                    .href(countrySite.getLink().getHref())
                                    .body(countrySite.getCountryName())
                                    .addExtraAttributes("class", countrySite.getFlagIcon().getCssClass())
                                    .addExtraAttributes("language", countrySite.getLanguage().getName())
                                    .addExtraAttributes("default", (countrySite.equals(defaultSite)))
                                    .build())
                            .collect(Collectors.toList()))
                    .build());
        }
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
                        .addExtraAttributes("desktopCSS", model.getCallToAction().getDesktopButtonType().getCss())
                        .addExtraAttributes("mobileCSS", model.getCallToAction().getMobileButtonType().getCss())
                        .build())
                .build();
    }
}
