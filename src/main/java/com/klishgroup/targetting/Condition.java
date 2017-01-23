package com.klishgroup.targetting;

import com.psddev.dari.db.Record;

import javax.servlet.http.HttpServletRequest;

public abstract class Condition extends Record {

    public abstract boolean matches(HttpServletRequest request);

    @Override
    public abstract String getLabel();
}
