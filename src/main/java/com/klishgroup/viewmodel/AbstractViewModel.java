package com.klishgroup.viewmodel;

import com.klishgroup.util.HttpRequest;
import com.klishgroup.util.RichTextProcessor;
import com.klishgroup.view.base.util.ConcatenatedView;
import com.klishgroup.view.base.util.RawHtmlView;
import com.psddev.cms.db.Site;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.servlet.CurrentSite;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractViewModel<M> extends ViewModel<M> {

    public static final String MODULE_VIEW_TYPE = "module";
    public static final String MAIN_VIEW_TYPE = "main";
    public static final String RTE_VIEW_TYPE = RichTextProcessor.RICH_TEXT_ELEMENT_VIEW_TYPE;

    @HttpRequest
    private HttpServletRequest request;

    @CurrentSite
    private Site site;

    public Site getSite() {
        return site;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public ConcatenatedView createRichTextView(String richText) {
        return createRichTextView(richText, false);
    }

    public ConcatenatedView createRichTextView(String richText, boolean renderLockedView) {

        if (richText == null) {
            return null;
        }

        return new ConcatenatedView.Builder()
                .addAllToItems(RichTextProcessor
                        .createDefault(richText)
                        .renderUnhandledRichTextElements(false)
                        .htmlViewFunction((String html) -> new RawHtmlView.Builder().html(html).build())
                        .richTextElementViewFunction(rte -> createView(RTE_VIEW_TYPE, rte))
                        .renderLockedView(renderLockedView)
                        .build())
                .build();
    }
}

