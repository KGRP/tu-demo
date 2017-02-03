package com.klishgroup.targetting.attribute;

import com.klishgroup.model.DemandBaseAttribute;
import com.klishgroup.targetting.ExtendedAttributeValue;
import com.psddev.dari.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class DemandBaseParameterExtendedAttributeValue extends ExtendedAttributeValue {

    @Required
    private DemandBaseAttribute demandBaseAttribute;

    public DemandBaseAttribute getDemandBaseAttribute() {
        return demandBaseAttribute;
    }

    public void setDemandBaseAttribute(DemandBaseAttribute demandBaseAttribute) {
        this.demandBaseAttribute = demandBaseAttribute;
    }

    @Override
    public String getAttributeValue(HttpServletRequest request) {
        if (ObjectUtils.isBlank(demandBaseAttribute)) {
            return null;
        }
        return request.getParameter(demandBaseAttribute.getAttribute());
    }

    @Override
    public String getLabel() {
        String label = "";
        if (demandBaseAttribute != null) {
            label += "The value of " + demandBaseAttribute.getLabel();
        }
        return label;
    }
}
