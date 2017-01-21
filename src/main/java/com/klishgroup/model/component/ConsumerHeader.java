package com.klishgroup.model.component;

import com.klishgroup.model.CallToAction;
import com.klishgroup.model.Link;
import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.ConsumerHeaderViewModel;
import com.psddev.cms.view.ViewBinding;

@ViewBinding(value = ConsumerHeaderViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class ConsumerHeader extends AbstractHeader {

    @Required
    private CallToAction callToAction;

    @Required
    private Link memberLogin;

    @Required
    private Link searchPage;

    public CallToAction getCallToAction() {
        return callToAction;
    }

    public void setCallToAction(CallToAction callToAction) {
        this.callToAction = callToAction;
    }

    public Link getMemberLogin() {
        return memberLogin;
    }

    public void setMemberLogin(Link memberLogin) {
        this.memberLogin = memberLogin;
    }

    public Link getSearchPage() {
        return searchPage;
    }

    public void setSearchPage(Link searchPage) {
        this.searchPage = searchPage;
    }
}
