package com.klishgroup.targetting.attribute;

import com.klishgroup.targetting.ExtendedAttributeValue;
import com.psddev.dari.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestParameterExtendedAttributeValue extends ExtendedAttributeValue {

    @Required
    private String requestParameterName;

    public String getRequestParameterName() {
        return requestParameterName;
    }

    public void setRequestParameterName(String requestParameterName) {
        this.requestParameterName = requestParameterName;
    }

    @Override
    public String getAttributeValue(HttpServletRequest request) {
        return (StringUtils.isBlank(requestParameterName)) ? null : request.getParameter(requestParameterName);
    }

    /**
     * Rule Verbalization
     * @return
     */
    @Override
    public String getLabel() {
        String label = "";
        if (requestParameterName != null) {
            label += requestParameterName;
        }
        return label;
    }
}
