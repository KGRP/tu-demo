package com.klishgroup.targetting;

import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class ExtendedAttribute extends Record {

    private static final String TAGGABLE_ATTRIBUTE = "com.klishgroup.targetting.Taggable/tg.tags/attribute";

    @Indexed
    @Required
    private String name;

    @Indexed
    @Recordable.JunctionField(TAGGABLE_ATTRIBUTE)
    private List<Tag> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        if (ObjectUtils.isBlank(tags)) {
            return new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
