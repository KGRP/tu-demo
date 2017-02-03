package com.klishgroup.targeted;

import com.klishgroup.model.component.BusinessHeader;
import com.klishgroup.targetting.Targeted;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
public class TargetedBusinessHeader extends Targeted.SingleContent {

    @Required
    private BusinessHeader defaultBusinessHeader;

    public BusinessHeader getDefaultBusinessHeader() {
        return defaultBusinessHeader;
    }

    public void setDefaultBusinessHeader(BusinessHeader defaultBusinessHeader) {
        this.defaultBusinessHeader = defaultBusinessHeader;
    }

    @Override
    public Record getDefaultModule() {
        return getDefaultBusinessHeader();
    }

    @Override
    public Class<? extends Record> getContentType() {
        return BusinessHeader.class;
    }
}
