package com.klishgroup.targetting;

import com.psddev.dari.db.Record;

import javax.servlet.http.HttpServletRequest;

public abstract class ExtendedAttributeValue extends Record {

    public abstract String getAttributeValue(HttpServletRequest request);

    @Override
    public abstract String getLabel();
}
