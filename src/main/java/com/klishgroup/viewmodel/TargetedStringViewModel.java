package com.klishgroup.viewmodel;

import com.klishgroup.model.DemandBaseAttribute;
import com.klishgroup.model.TargetedString;
import com.klishgroup.view.base.util.RawHtmlView;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

public class TargetedStringViewModel extends AbstractViewModel<TargetedString> implements RawHtmlView {

    @Override
    public Object getHtml() {

        DemandBaseAttribute demandBaseAttribute = model.getDemandBaseAttribute();
        String value = model.getDefaultValue();

        if (!ObjectUtils.isBlank(demandBaseAttribute)) {
            String targetedValue = getRequest().getParameter(demandBaseAttribute.getAttribute());

            if (!StringUtils.isBlank(targetedValue)) {
                return targetedValue;
            }
        }
        return value;
    }
}
