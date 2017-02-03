package com.klishgroup.targetting.condition;

import com.klishgroup.model.DemandBaseAttribute;
import com.klishgroup.targetting.Condition;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class DemandBaseParameterExistsCondition extends Condition {

    @Required
    private DemandBaseAttribute demandBaseAttribute;

    public DemandBaseAttribute getDemandBaseAttribute() {
        return demandBaseAttribute;
    }

    public void setDemandBaseAttribute(DemandBaseAttribute demandBaseAttribute) {
        this.demandBaseAttribute = demandBaseAttribute;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (ObjectUtils.isBlank(demandBaseAttribute)) {
            return Boolean.FALSE;
        }
        return !StringUtils.isBlank(request.getParameter(demandBaseAttribute.getAttribute()));
    }

    /**
     * Rule Verbalization
     * @return
     */
    @Override
    public String getLabel() {

        String label = "";
        if (demandBaseAttribute != null) {
            label += demandBaseAttribute.getLabel() + " exists?";
        }
        return label;
    }
}
