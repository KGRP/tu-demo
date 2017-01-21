package com.klishgroup.model.page;

import com.klishgroup.model.Resource;
import com.klishgroup.model.component.AbstractHeader;
import com.klishgroup.model.component.Module;
import com.klishgroup.model.component.RawContent;
import com.klishgroup.viewmodel.PageViewModel;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.PageFilter;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.personalization.Audience;
import com.psddev.personalization.AudienceParticipant;
import com.psddev.personalization.Variation;

import java.util.ArrayList;
import java.util.List;

@ViewBinding(value = PageViewModel.class, types = { PageFilter.PAGE_VIEW_TYPE })
@Recordable.DisplayName("Page")
public class AbstractPage extends Content {
    @Required
    private String name;

    private AbstractHeader header;

    private List<Module> modules;

    @ToolUi.RichText
    private RawContent footer;

    @ToolUi.Tab("Resources")
    private List<Resource> headResources;
    @ToolUi.Tab("Resources")
    @Types({Resource.JsResource.class})
    private List<Resource> bodyResources;
    @ToolUi.Tab("Resources")
    @ToolUi.CodeType("text/css")
    private String extraCss;
    @ToolUi.Tab("Resources")
    @ToolUi.CodeType("text/javascript")
    private String extraJs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractHeader getHeader() {
        return header;
    }

    public void setHeader(AbstractHeader header) {
        this.header = header;
    }

    public List<Module> getModules() {
        if (ObjectUtils.isBlank(modules)) {
            return new ArrayList<>();
        }
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public RawContent getFooter() {
        return footer;
    }

    public void setFooter(RawContent footer) {
        this.footer = footer;
    }

    public List<Resource> getHeadResources() {
        if (ObjectUtils.isBlank(headResources)) {
            return new ArrayList<>();
        }
        return headResources;
    }

    public void setHeadResources(List<Resource> headResources) {
        this.headResources = headResources;
    }

    public List<Resource> getBodyResources() {
        if (ObjectUtils.isBlank(bodyResources)) {
            return new ArrayList<>();
        }
        return bodyResources;
    }

    public void setBodyResources(List<Resource> bodyResources) {
        this.bodyResources = bodyResources;
    }

    public String getExtraCss() {
        return extraCss;
    }

    public void setExtraCss(String extraCss) {
        this.extraCss = extraCss;
    }

    public String getExtraJs() {
        return extraJs;
    }

    public void setExtraJs(String extraJs) {
        this.extraJs = extraJs;
    }
}

