package com.klishgroup.targetting.model;

import com.klishgroup.model.page.BusinessPage;
import com.klishgroup.targetting.TargetedInterface;
import com.klishgroup.targetting.viewmodel.TargetedBusinessPageViewModel;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.PageFilter;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Record;

@ViewBinding(value = TargetedBusinessPageViewModel.class, types = { PageFilter.PAGE_VIEW_TYPE })
@ToolUi.Main
public class TargetedBusinessPage extends Content implements TargetedInterface.SingleContent {

    @Required
    private BusinessPage defaultBusinessPage;

    public BusinessPage getDefaultBusinessPage() {
        return defaultBusinessPage;
    }

    public void setDefaultBusinessPage(BusinessPage defaultBusinessPage) {
        this.defaultBusinessPage = defaultBusinessPage;
    }

    @Override
    public Record getDefaultModule() {
        return getDefaultBusinessPage();
    }

    @Override
    public Class<? extends Record> getContentType() {
        return BusinessPage.class;
    }
}
