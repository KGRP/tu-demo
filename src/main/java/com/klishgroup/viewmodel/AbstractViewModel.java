package com.klishgroup.viewmodel;

import com.klishgroup.util.HttpRequest;
import com.psddev.cms.view.ViewModel;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractViewModel<M> extends ViewModel<M> {

    public static final String MODULE_VIEW_TYPE = "module";
    public static final String MAIN_VIEW_TYPE = "main";

    @HttpRequest
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }
}

