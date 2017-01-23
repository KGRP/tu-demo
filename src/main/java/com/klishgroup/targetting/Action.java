package com.klishgroup.targetting;

import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Record;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class Action extends Record {

    public abstract List<Record> execute(HttpServletRequest request, ObjectType objectType);

    @Override
    public abstract String getLabel();
}
