package com.klishgroup.targetting.condition;

import com.klishgroup.targetting.Condition;
import com.psddev.dari.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestParameterExistsCondition extends Condition {

    @Required
    private String requestParameterName;

    public String getRequestParameterName() {
        return requestParameterName;
    }

    public void setRequestParameterName(String requestParameterName) {
        this.requestParameterName = requestParameterName;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return !StringUtils.isBlank(request.getParameter(getRequestParameterName()));
    }

    /**
     * Rule Verbalization
     * @return
     */
    @Override
    public String getLabel() {

        String label = "";
        if (getRequestParameterName() != null) {
            label += "Request Parameter " + getRequestParameterName() + " exists?";
        }
        return label;
    }
}
