package com.klishgroup.model;

import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

public class Meta extends Record {

    @Embedded
    @Required
    private MetaType type;

    @Required
    private String content;

    public MetaType getType() {
        return type;
    }

    public void setType(MetaType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class MetaType extends Record {

        @Required
        private MetaTypeAttribute attribute;

        @Required
        private String attributeValue;

        public MetaTypeAttribute getAttribute() {
            return attribute;
        }

        public void setAttribute(MetaTypeAttribute attribute) {
            this.attribute = attribute;
        }

        public String getAttributeValue() {
            return attributeValue;
        }

        public void setAttributeValue(String attributeValue) {
            this.attributeValue = attributeValue;
        }
    }

    public enum MetaTypeAttribute {
        HTTP_EQUIV("http-equiv"),
        NAME("name"),
        PROPERTY("property");

        private String value;

        MetaTypeAttribute(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public String getLabel(){

        String label = "";

        if (!ObjectUtils.isBlank(type)) {

            MetaTypeAttribute attribute = type.getAttribute();
            if (!ObjectUtils.isBlank(attribute)) {
                label += attribute.getValue() + "=";
            }

            String attributeValue = type.getAttributeValue();
            if (!ObjectUtils.isBlank(attributeValue)) {
                label += attributeValue;
            }
        }

        if (!StringUtils.isBlank(content)) {
            label += "  => content=" + content;
        }

        return label;
    }
}
