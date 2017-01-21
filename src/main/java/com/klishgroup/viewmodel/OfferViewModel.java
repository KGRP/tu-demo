package com.klishgroup.viewmodel;

import com.klishgroup.model.component.Offer;
import com.klishgroup.view.base.util.RawHtmlView;
import com.klishgroup.view.component.OfferView;

public class OfferViewModel extends AbstractViewModel<Offer> implements OfferView {

    @Override
    public Object getContent() {
        return new RawHtmlView.Builder()
                .html(model.getContent())
                .build();
    }
}
