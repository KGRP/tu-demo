package com.klishgroup.viewmodel;

import com.klishgroup.model.component.Footer;
import com.klishgroup.view.base.util.ConcatenatedView;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.common.FooterView;
import com.klishgroup.view.component.FooterCallToActionView;
import com.klishgroup.view.component.FooterTitledLinkListView;
import com.psddev.dari.util.ObjectUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class FooterViewModel extends AbstractViewModel<Footer> implements FooterView {

    @Override
    public Object getTitledSections() {
        return new ConcatenatedView.Builder()
                .addAllToItems(model.getLinkSections().stream()
                .filter(Objects::nonNull)
                .map(linkSection -> new FooterTitledLinkListView.Builder()
                                            .title(linkSection.getTitle())
                                            .addAllToLinks(linkSection.getLinks().stream()
                                                            .filter(Objects::nonNull)
                                                            .map(link -> new LinkView.Builder()
                                                                            .href(link.getHref())
                                                                            .body(link.getText())
                                                                            .build())
                                                            .filter(Objects::nonNull)
                                                            .collect(Collectors.toList()))
                                            .build())
                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Collection<?> getMiscellaneousLinks() {
        return model.getMiscellaneousLinks().stream()
                        .filter(Objects::nonNull)
                        .map(link -> new LinkView.Builder()
                                        .href(link.getHref())
                                        .body(link.getText())
                                        .build())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }

    @Override
    public Object getCallToAction() {
        return new FooterCallToActionView.Builder()
                .title(model.getCta().getTitle())
                .link(new LinkView.Builder()
                        .href(model.getCta().getLink().getHref())
                        .body(model.getCta().getLink().getText())
                        .build())
                .build();
    }

    @Override
    public Collection<?> getSocialLinks() {
        return model.getSocialLinks().stream()
                        .filter(Objects::nonNull)
                        .map(link -> new LinkView.Builder()
                                        .href(link.getHref())
                                        .body(link.getText())
                                        .icon((!ObjectUtils.isBlank(link.getIcon())) ?
                                                link.getIcon().getCssClass() : null)
                                        .build())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }
}
