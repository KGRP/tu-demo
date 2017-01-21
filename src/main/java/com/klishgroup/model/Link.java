package com.klishgroup.model;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

@Recordable.Embedded
@Record.LabelFields("text")
public abstract class Link extends Record {

    @ToolUi.Placeholder(dynamicText = "${content.textPlaceholder}", editable = true)
    protected String text;

    protected Icon icon;

    protected Target target;

    public enum Target {
        _blank("New Window/Tab"),
        _self("Same Window/Tab");

        private String value;

        private Target(String v) {
            value = v;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public String getTarget() {
        return target != null ? target.name() : null;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getText() {
        return !StringUtils.isBlank(text) ? text : "";
    }

    public void setText(String text) {
        this.text = text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public abstract String getHref();

    // For CMS UI
    public String getTextPlaceholder() {
        return "";
    }

    /**
     * On-site, internal content link object.
     */
    public static class InternalLink extends Link {

        @Required
        @ToolUi.OnlyPathed
        @ToolUi.DisplayFirst
        private Content content;

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }

        @Override
        public String getHref() {
            if (content == null) {
                return null;
            }

            return content.getPermalink();
        }

        @Override
        public String getText() {
            return text;
        }
    }

    /**
     * A simple linked text object, presumably to an external URL (though not
     * enforced).
     */
    @Embedded
    public static class ExternalLink extends Link {

        @ToolUi.DisplayFirst
        @ToolUi.Note("Fully qualified URL; e.g., 'http://www.example.com/'")
        @Required
        private String url;

        public ExternalLink() {
            // default to open new window/tab
            this.setTarget(Target._blank);
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String getHref() {
            return url;
        }

        @Override
        public void beforeCommit() {

            //Validate URL
            if (!StringUtils.isBlank(url)) {
                try {
                    new URI(url);
                } catch (URISyntaxException e) {
                    getState().addError(getState().getField("url"), "Invalid URL!");
                }
            }
        }
    }
}
