package com.klishgroup.viewmodel;

import com.klishgroup.model.Image;
import com.klishgroup.model.component.HomePageLedeV2;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.base.util.RawHtmlView;
import com.klishgroup.view.component.HomePageLedeView;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

public class HomePageLedeV2ViewModel extends AbstractViewModel<HomePageLedeV2> implements HomePageLedeView {

    @Override
    public Object getAlreadyMemberLink() {
        return new RawHtmlView.Builder()
                .html(model.getAlreadyMemberLink())
                .build();
    }

    @Override
    public Object getCtaButton() {
        return new LinkView.Builder()
                .body(model.getCtaButton().getText())
                .href(model.getCtaButton().getHref())
                .icon((ObjectUtils.isBlank(model.getCtaButton().getIcon())) ? null : model.getCtaButton().getIcon().getCssClass())
                .build();
    }

    @Override
    public Object getHeadline1() {
        return createRichTextView(model.getHeadline1());
    }

    @Override
    public Object getHeadline2() {
        return model.getHeadline2();
    }

    @Override
    public Object getHeadline3() {
        return model.getHeadline3();
    }

    @Override
    public Object getImage() {
        if (ObjectUtils.isBlank(model.getImage())) {
            return null;
        }
        Record imageRecord = model.getImage().getTargetedModule(getRequest());
        return (!ObjectUtils.isBlank(imageRecord))
                ? ObjectUtils.to(Image.class, imageRecord).getFile().getPublicUrl() : null;
    }

    @Override
    public Object getMobileImage() {
        if (ObjectUtils.isBlank(model.getImageMobile())) {
            return null;
        }
        Record imageRecord = model.getImageMobile().getTargetedModule(getRequest());
        return (!ObjectUtils.isBlank(imageRecord))
                ? ObjectUtils.to(Image.class, imageRecord).getFile().getPublicUrl() : null;
    }
}