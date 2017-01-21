package com.klishgroup.viewmodel;

import com.klishgroup.model.component.BannerCarousel;
import com.klishgroup.model.component.BannerCarouselSlide;
import com.klishgroup.view.base.util.LinkView;
import com.klishgroup.view.component.BannerCarouselSlideView;
import com.klishgroup.view.component.BannerCarouselView;
import com.psddev.dari.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class BannerCarouselViewModel extends AbstractViewModel<BannerCarousel> implements BannerCarouselView {

    @Override
    public Collection<?> getSlides() {
        return createBannerCarouselSlides();
    }

    private List<BannerCarouselSlideView> createBannerCarouselSlides() {

        List<BannerCarouselSlideView> slidesViews = new ArrayList<>();
        List<BannerCarouselSlide> slides = model.getSlides();

        IntStream.range(0, slides.size())
                .forEach(index -> {
                    BannerCarouselSlide slide = slides.get(index);
                    slidesViews.add(new BannerCarouselSlideView.Builder()
                            .eyebrowTitle(slide.getEyebrowTitle())
                            .title(slide.getTitle())
                            .body(slide.getBody())
                            .ctaButton((ObjectUtils.isBlank(slide.getCtaButton()) ? null : new LinkView.Builder()
                                    .href(slide.getCtaButton().getHref())
                                    .body(slide.getCtaButton().getText())
                                    .icon(slide.getCtaButton().getIcon().getCssClass())
                                    .build()))
                            .image((ObjectUtils.isBlank(slide.getDesktopImage()) ? null : slide.getDesktopImage().getPublicUrl()))
                            .mobileImage((ObjectUtils.isBlank(slide.getMobileImage()) ? null : slide.getMobileImage().getPublicUrl()))
                            .active((index == 0))
                            .slideTo(index)
                            .build());
                });

        return slidesViews;
    }
}
