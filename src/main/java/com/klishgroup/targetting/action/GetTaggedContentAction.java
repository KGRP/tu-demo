package com.klishgroup.targetting.action;

import com.klishgroup.targetting.Action;
import com.klishgroup.targetting.ExtendedAttributeValue;
import com.klishgroup.targetting.ExtendedAttribute;
import com.klishgroup.targetting.Tag;
import com.klishgroup.targetting.Taggable;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Grouping;
import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetTaggedContentAction extends Action {

    private static final String TAGGABLE_ATTRIBUTE = ObjectType.getInstance(Taggable.class).getInternalName() + "/tg.tags/attribute";
    private static final String TAGGABLE_VALUES = ObjectType.getInstance(Taggable.class).getInternalName() + "/tg.tags/values";

    @Required
    @ToolUi.DisplayFirst
    private ExtendedAttribute attribute;

    public ExtendedAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ExtendedAttribute attribute) {
        this.attribute = attribute;
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
        return label;
    }

    @Override
    public List<Record> execute(HttpServletRequest request, ObjectType objectType) {

        ExtendedAttributeValue value = getValue();

        if (ObjectUtils.isBlank(value) || ObjectUtils.isBlank(value.getOriginalOrSimulatedAttributeValue(request))) {
            return null;
        }

        String tagValue = value.getOriginalOrSimulatedAttributeValue(request);

        return Query.from(Record.class).where("_type = ? and " + TAGGABLE_ATTRIBUTE + " = ? and " + TAGGABLE_VALUES + " = ?",
                objectType, getAttribute(), tagValue).sortDescending("cms.content.publishDate").selectAll();
    }

    @Override
    public Set<String> getValuesForPreviewSimulation() {

        Set<String> values = new HashSet<>();

        List<Grouping<Taggable>> groupings = Query.from(Taggable.class)
                .where(TAGGABLE_ATTRIBUTE + " = ?", getAttribute())
                .groupBy(TAGGABLE_VALUES);

        for (Grouping<Taggable> grouping : groupings) {
            String value = (String) grouping.getKeys().get(0);

            if (!StringUtils.isBlank(value)) {
                values.add(value);
            }
        }
        return values;
    }
}
