package com.klishgroup.model.component;

import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.OfferViewModel;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;

@ViewBinding(value = OfferViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class Offer extends Content implements Module {

    @Required
    //@ToolUi.Note("CMS Only")
    private String name;

    @ToolUi.RichText
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
