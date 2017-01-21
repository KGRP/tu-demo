package com.klishgroup.model.targetting;

import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Recordable.Embedded
@Recordable.LabelFields("attribute.name")
public class Tag extends Record {

    @Required
    @Indexed
    private ExtendedAttribute attribute;

    @Minimum(1)
    @Indexed
    private List<String> values;

    public ExtendedAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ExtendedAttribute attribute) {
        this.attribute = attribute;
    }

    public List<String> getValues() {
        if (ObjectUtils.isBlank(values)) {
            return new ArrayList<>();
        }
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String getLabel() {

        String label = "";
        if (getAttribute() != null) {
            label += getAttribute().getLabel();
        }

        if (getValues() != null) {
            if (!StringUtils.isBlank(label)) {
                label += " -> ";
            }

            for (String value : getValues()) {
                label += value + ", ";
            }
        }
        return label;
    }
}
