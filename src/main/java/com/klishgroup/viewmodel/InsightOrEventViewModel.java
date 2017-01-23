package com.klishgroup.viewmodel;

import com.klishgroup.model.InsightOrEvent;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.component.InsightOrEventView;

public class InsightOrEventViewModel extends AbstractViewModel<InsightOrEvent> implements InsightOrEventView {

    @Override
    public Object getEyebrowTitle() {
        return model.getEyebrowTitle();
    }

    @Override
    public Object getDescription() {
        return model.getDescription();
    }

    @Override
    public Object getThumbnail() {
        return model.getThumbnail().getPublicUrl();
    }

    @Override
    public Object getCtaButton() {
        return new LinkView.Builder()
                            .href(model.getCta().getHref())
                            .body(model.getCta().getText())
                            .build();
    }
}
