package com.klishgroup.targetting;

import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Recordable.LabelFields("name")
public abstract class Targeted extends Record {

    @Required
    private String name;

    @Required
    @Embedded
    private RenderingMode renderingMode;

    public RenderingMode getRenderingMode() {
        return renderingMode;
    }

    public void setRenderingMode(RenderingMode renderingMode) {
        this.renderingMode = renderingMode;
    }

    public abstract Class<? extends Record> getContentType();

    @Abstract
    public abstract static class SingleContent extends Targeted {

        public abstract Record getDefaultModule();

        public Record getTargetedModule(HttpServletRequest request) {
            List<Record> modules = generateModuleList(request, getDefaultModule());
            if (!ObjectUtils.isBlank(modules) && modules.size() > 0) {
                return modules.get(0);
            }
            return null;
        }
    }

    @Abstract
    public abstract static class MultipleContent extends Targeted {

        public abstract List<Record> getDefaultModules();

        public List<Record> getTargetedModules(HttpServletRequest request) {
            return generateModuleList(request, getDefaultModules());
        }
    }

    protected List<Record> generateModuleList(HttpServletRequest request, Record module) {
        List<Record> modules = new ArrayList<>();
        modules.add(module);
        return generateModuleList(request, modules);
    }

    protected List<Record> generateModuleList(HttpServletRequest request, List<Record> modules) {

        ObjectType objectType = ObjectType.getInstance(Record.class);

        Class objectClass = getContentType();
        if (!ObjectUtils.isBlank(objectClass)) {
            objectType = ObjectType.getInstance(objectClass);
        }
        return renderingMode.generateModuleList(request, modules, objectType);
    }
}
