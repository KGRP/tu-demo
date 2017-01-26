package com.klishgroup.targetting;

import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Rule extends Record {

    @Required
    private Condition condition;

    @Required
    private Action action;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Record> execute(HttpServletRequest request, ObjectType contentType) {

        List<Record> modules = new ArrayList<>();

        if (!ObjectUtils.isBlank(condition) && condition.matches(request)) {
            modules =  action.execute(request, contentType);
        }
        return (!ObjectUtils.isBlank(modules)) ? modules : new ArrayList<>();
    }

    @Override
    public String getLabel() {

        String label = "";
        if (getCondition() != null) {
            label += "If " + getCondition().getLabel();
        }

        if (getAction() != null) {
            if (!StringUtils.isBlank(label)) {
                label += " -> ";
            }
            label += getAction().getLabel();
        }
        return label;
    }
}
