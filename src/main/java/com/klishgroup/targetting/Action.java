package com.klishgroup.targetting;

import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Record;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public abstract class Action extends Record {

    @Required
    private ExtendedAttributeValue value;

    public ExtendedAttributeValue getValue() {
        return value;
    }

    public void setValue(ExtendedAttributeValue value) {
        this.value = value;
    }

    public abstract List<Record> execute(HttpServletRequest request, ObjectType objectType);

    public abstract Set<String> getValuesForPreviewSimulation();

    @Override
    public abstract String getLabel();
}
