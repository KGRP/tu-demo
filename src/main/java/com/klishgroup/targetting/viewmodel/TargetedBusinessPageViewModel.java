package com.klishgroup.targetting.viewmodel;

import com.klishgroup.targetting.model.TargetedBusinessPage;
import com.klishgroup.view.base.util.ConcatenatedView;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.psddev.cms.db.PageFilter;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TargetedBusinessPageViewModel extends AbstractViewModel<TargetedBusinessPage> implements ConcatenatedView {

    @Override
    public Collection<?> getItems() {
        Record record = model.getTargetedModule(getRequest());

        if (ObjectUtils.isBlank(record)) {
            return null;
        }

        List<Object> items = new ArrayList<>();
        items.add(createView(PageFilter.PAGE_VIEW_TYPE, record));

        return items;
    }
}
