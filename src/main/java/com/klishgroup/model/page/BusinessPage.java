package com.klishgroup.model.page;

import com.klishgroup.targetting.Taggable;
import com.klishgroup.viewmodel.PageViewModel;
import com.psddev.cms.db.PageFilter;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;

@ToolUi.Main
@ViewBinding(value = PageViewModel.class, types = { PageFilter.PAGE_VIEW_TYPE })
public class BusinessPage extends AbstractPage implements Taggable {
}
