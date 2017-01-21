package com.klishgroup.model.targetting;

import com.psddev.dari.db.Record;

public class ExtendedAttribute extends Record {

    @Indexed
    @Required
    private String name;
}
