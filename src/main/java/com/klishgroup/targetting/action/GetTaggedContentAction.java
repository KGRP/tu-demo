package com.klishgroup.targetting.action;

import com.klishgroup.targetting.Action;
import com.klishgroup.targetting.ExtendedAttributeValue;
import com.klishgroup.targetting.ExtendedAttribute;
import com.klishgroup.targetting.Taggable;
import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetTaggedContentAction extends Action {

    @Required
    private ExtendedAttribute attribute;

    @Required
    private ExtendedAttributeValue value;

    public ExtendedAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ExtendedAttribute attribute) {
        this.attribute = attribute;
    }

    public ExtendedAttributeValue getValue() {
        return value;
    }

    public void setValue(ExtendedAttributeValue value) {
        this.value = value;
    }

    /**
     * Rule Verbalization
     * @return
     */
    @Override
    public String getLabel() {

        String label = "";
        if (getAttribute() != null) {
            label += "Get the content tagged by " + getAttribute().getLabel();
        }

        /*
        if (getValue() != null) {
            if (!StringUtils.isBlank(label)) {
                label += " where ";
            }
            label += getAttribute().getLabel() + " equals " + getValue().getLabel();
        }
        */
        return label;
    }

    @Override
    public List<Record> execute(HttpServletRequest request, ObjectType objectType) {

        ExtendedAttributeValue value = getValue();

        if (ObjectUtils.isBlank(value) || ObjectUtils.isBlank(value.getAttributeValue(request))) {
            return null;
        }

        String taggableAttribute = ObjectType.getInstance(Taggable.class).getInternalName() + "/tg.tags/attribute";
        String taggableValues = ObjectType.getInstance(Taggable.class).getInternalName() + "/tg.tags/values";

        return Query.from(Record.class).where("_type = ? and " + taggableAttribute + " = ? and " + taggableValues + " = ?",
                objectType, getAttribute(), getValue().getAttributeValue(request)).sortDescending("cms.content.publishDate").selectAll();
    }
}
