package com.klishgroup.targeted;

import com.klishgroup.model.component.HomePageLede;
import com.klishgroup.model.component.Module;
import com.klishgroup.targetting.Targeted;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
public class TargetedHomePageLede extends Targeted.SingleContent implements Module {

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
