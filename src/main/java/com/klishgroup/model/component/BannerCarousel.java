package com.klishgroup.model.component;

import com.klishgroup.viewmodel.AbstractViewModel;
import com.klishgroup.viewmodel.BannerCarouselViewModel;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@ViewBinding(value = BannerCarouselViewModel.class, types = { AbstractViewModel.MODULE_VIEW_TYPE })
public class BannerCarousel extends Content implements Module {

    @Recordable.Required
    @ToolUi.Note("CMS Only")
    private String name;

    @Recordable.Embedded
    @Minimum(1)
    private List<BannerCarouselSlide> slides;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BannerCarouselSlide> getSlides() {
        if (ObjectUtils.isBlank(slides)) {
            return new ArrayList<>();
        }
        return slides;
    }

    public void setSlides(List<BannerCarouselSlide> slides) {
        this.slides = slides;
    }
}
