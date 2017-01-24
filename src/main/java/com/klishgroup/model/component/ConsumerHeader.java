package com.klishgroup.model.component;

import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.ConsumerHeaderViewModel;
import com.psddev.cms.view.ViewBinding;

@ViewBinding(value = ConsumerHeaderViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class ConsumerHeader extends AbstractHeader {
}
