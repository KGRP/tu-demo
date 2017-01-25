package com.klishgroup.targetting.attribute;

import com.klishgroup.targetting.ExtendedAttributeValue;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;

public class DayOfWeekExtendedAttributeValue extends ExtendedAttributeValue {

    @Required
    private DayOfWeek day;

    @Override
    public String getAttributeValue(HttpServletRequest request) {
        return day.name();
    }

    /**
     * Rule Verbalization
     * @return
     */
    @Override
    public String getLabel() {
        String label = "";
        if (day != null) {
            label += day.name();
        }
        return label;
    }
}
