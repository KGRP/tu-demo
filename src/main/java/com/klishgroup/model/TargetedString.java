package com.klishgroup.model;

import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.TargetedStringViewModel;
import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Recordable.DisplayName("Targeted String")
@RichTextElement.Tag(value = TargetedString.TAG_NAME)
@ViewBinding(value = TargetedStringViewModel.class, types = AbstractViewModel.RTE_VIEW_TYPE)
public class TargetedString extends RichTextElement {

    private static final String DEMAND_BASE_ATTRIBUTE_STRING = "demand-base-attribute";

    public static final String TAG_NAME = "bsp.targetedstring";

    private DemandBaseAttribute demandBaseAttribute;

    private String defaultValue;

    public DemandBaseAttribute getDemandBaseAttribute() {
        return demandBaseAttribute;
    }

    public void setDemandBaseAttribute(DemandBaseAttribute demandBaseAttribute) {
        this.demandBaseAttribute = demandBaseAttribute;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public void fromBody(String body) {
        super.fromBody(body);
        if (!StringUtils.isBlank(body)) {
            setDefaultValue(body);
        }
    }

    @Override
    public String toBody() {
        return defaultValue != null ? defaultValue : null;
    }

    @Override
    public void fromAttributes(Map<String, String> map) {
        if (ObjectUtils.isBlank(map)) {
            return;
        }
        if (map.containsKey(DEMAND_BASE_ATTRIBUTE_STRING)) {
            setDemandBaseAttribute(DemandBaseAttribute.fromString(map.get(DEMAND_BASE_ATTRIBUTE_STRING)));
        }
    }

    @Override
    public Map<String, String> toAttributes() {
        Map<String, String> attributes = new HashMap<>();

        DemandBaseAttribute demandBaseAttribute = getDemandBaseAttribute();

        if (!ObjectUtils.isBlank(demandBaseAttribute)) {
            attributes.put(DEMAND_BASE_ATTRIBUTE_STRING, demandBaseAttribute.getAttribute());
        }
        return attributes;
    }
}
