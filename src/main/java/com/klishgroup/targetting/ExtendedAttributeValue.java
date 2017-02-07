package com.klishgroup.targetting;

import com.psddev.cms.db.PageFilter;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class ExtendedAttributeValue extends Record {

    public String getOriginalOrSimulatedAttributeValue(HttpServletRequest request) {

        if (PageFilter.Static.isPreview(request)) {
            String attributeValue = request.getParameter(this.getId().toString());
            if (!StringUtils.isBlank(attributeValue)) {
                return attributeValue;
            }
        }
        return getAttributeValue(request);
    }

    public abstract String getAttributeValue(HttpServletRequest request);

    @Override
    public abstract String getLabel();
}
