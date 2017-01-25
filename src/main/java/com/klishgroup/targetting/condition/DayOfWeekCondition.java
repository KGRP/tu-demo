package com.klishgroup.targetting.condition;

import com.klishgroup.targetting.Condition;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;

public class DayOfWeekCondition extends Condition {

    @Required
    private DayOfWeek day;

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return OffsetDateTime.now().getDayOfWeek().equals(day);
    }

    /**
     * Rule Verbalization
     * @return
     */
    @Override
    public String getLabel() {
        String label = "";
        if (day != null) {
            label += "Today is " + day.name() + "?";
        }
        return label;
    }
}
