package com.klishgroup.viewmodel;

import com.klishgroup.model.component.HomePageLede;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.base.util.RawHtmlView;
import com.klishgroup.view.component.HomePageLedeView;
import com.psddev.dari.util.ObjectUtils;

public class HomePageLedeViewModel extends AbstractViewModel<HomePageLede> implements HomePageLedeView {

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
        return model.getHeadline1();
    }

    @Override
    public Object getHeadline2() {
        return model.getHeadline2();
    }

    @Override
    public Object getHeadline3() {
        return model.getHeadline3();
        //return null;
    }

    @Override
    public Object getImage() {
        if (ObjectUtils.isBlank(model.getImage())) {
            return null;
        }
        return model.getImage().getPublicUrl();
    }

    @Override
    public Object getMobileImage() {
        if (ObjectUtils.isBlank(model.getImageMobile())) {
            return null;
        }
        return model.getImageMobile().getPublicUrl();
    }
}
