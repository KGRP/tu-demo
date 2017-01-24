package com.klishgroup.targetting;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Modification;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public interface Taggable extends Recordable {

    default List<Tag> getTags() {
        return this.as(Taggable.Data.class).getTags();
    }

    @Recordable.BeanProperty("taggable")
    @Recordable.FieldInternalNamePrefix("tg.")
    class Data extends Modification<Taggable> {

        @ToolUi.Tab("Tagging")
        @Indexed
        private List<Tag> tags;

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
}
