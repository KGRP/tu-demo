package com.klishgroup.model;

public enum DemandBaseAttribute {

    COMPANY_NAME("Company Name", "company_name"),
    FORTUNE_1000("Fortune 1000", "fortune_1000"),
    FORTUNE_2000("Forbes 2000", "forbes_2000"),
    INDUSTRY("Industry", "industry");

    private String label;
    private String attribute;

    DemandBaseAttribute(String label, String attribute) {
        this.label = label;
        this.attribute = attribute;
    }

    public String getLabel() {
        return label;
    }

    public String getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return label;
    }

    public static DemandBaseAttribute fromString(String attribute) {
        if (attribute != null) {
            for (DemandBaseAttribute d : DemandBaseAttribute.values()) {
                if (attribute.equalsIgnoreCase(d.attribute)) {
                    return d;
                }
            }
        }
        return null;
    }
}
