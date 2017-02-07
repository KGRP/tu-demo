package com.klishgroup.targetting.model;

import com.klishgroup.model.Image;
import com.klishgroup.targetting.Targeted;
import com.klishgroup.targetting.TargetedInterface;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
public class TargetedImage extends Record implements TargetedInterface.SingleContent {

    @Required
    private Image defaultImage;

    public Image getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Image defaultImage) {
        this.defaultImage = defaultImage;
    }

    @Override
    public Record getDefaultModule() {
        return getDefaultImage();
    }

    @Override
    public Class<? extends Record> getContentType() {
        return Image.class;
    }
}
