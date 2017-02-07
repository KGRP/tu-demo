package com.klishgroup.targetting.model;

import com.klishgroup.model.component.BusinessHeader;
import com.klishgroup.targetting.Targeted;
import com.klishgroup.targetting.TargetedInterface;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;

public class TargetedBusinessHeader extends Record implements TargetedInterface.SingleContent {

    @Required
    private BusinessHeader defaultBusinessHeader;

    public BusinessHeader getDefaultBusinessHeader() {
        return defaultBusinessHeader;
    }

    public void setDefaultBusinessHeader(BusinessHeader defaultBusinessHeader) {
        this.defaultBusinessHeader = defaultBusinessHeader;
    }

    @Indexed
    @Override
    public Record getDefaultModule() {
        return getDefaultBusinessHeader();
    }

    @Override
    public Class<? extends Record> getContentType() {
        return BusinessHeader.class;
    }
}
