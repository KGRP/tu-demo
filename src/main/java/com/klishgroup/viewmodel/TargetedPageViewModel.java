package com.klishgroup.viewmodel;

import com.klishgroup.model.page.AbstractTargetedPage;
import com.klishgroup.view.base.util.ConcatenatedView;
import com.psddev.cms.db.PageFilter;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TargetedPageViewModel extends AbstractViewModel<AbstractTargetedPage> implements ConcatenatedView {

    @Override
    public Collection<?> getItems() {
        List<Object> pages = new ArrayList<>();

        if (ObjectUtils.isBlank(model.getTargetedProductPage())) {
            return null;
        }

        pages.add(createView(PageFilter.PAGE_VIEW_TYPE, model.getTargetedProductPage().getModule()));
        return pages;
    }
}
