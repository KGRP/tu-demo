package com.klishgroup.targetting.model;

import com.klishgroup.model.component.HomePageLede;
import com.klishgroup.model.component.Module;
import com.klishgroup.targetting.Targeted;
import com.klishgroup.targetting.TargetedInterface;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;

public class TargetedHomePageLede extends Record implements TargetedInterface.SingleContent, Module {

    @Required
    private HomePageLede defaultHomepageLede;

    public HomePageLede getDefaultHomepageLede() {
        return defaultHomepageLede;
    }

    public void setDefaultHomepageLede(HomePageLede defaultHomepageLede) {
        this.defaultHomepageLede = defaultHomepageLede;
    }

    @Override
    public Record getDefaultModule() {
        return getDefaultHomepageLede();
    }

    @Override
    public Class<? extends Record> getContentType() {
        return HomePageLede.class;
    }

}
