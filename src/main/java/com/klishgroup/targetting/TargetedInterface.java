package com.klishgroup.targetting;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Modification;
import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface TargetedInterface extends Recordable {

    default String getName() {
        return this.as(TargetedInterface.Data.class).getName();
    }

    default RenderingMode getRenderingMode() {
        return this.as(TargetedInterface.Data.class).getRenderingMode();
    }

    Class<? extends Record> getContentType();

    @Recordable.BeanProperty("targeted")
    @Recordable.FieldInternalNamePrefix("tgt.")
    @Recordable.LabelFields("tgt.name")
    class Data extends Modification<TargetedInterface> {

        @ToolUi.DisplayFirst
        @Required
        private String name;

        @ToolUi.DisplayAfter("tgt.name")
        @Embedded
        @Required
        private RenderingMode renderingMode;

        public RenderingMode getRenderingMode() {
            return renderingMode;
        }

        public void setRenderingMode(RenderingMode renderingMode) {
            this.renderingMode = renderingMode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    interface SingleContent extends TargetedInterface {

        Record getDefaultModule();

        default Record getTargetedModule(HttpServletRequest request) {
            List<Record> modules = generateModuleList(request, getDefaultModule());
            if (!ObjectUtils.isBlank(modules) && modules.size() > 0) {
                return modules.get(0);
            }
            return null;
        }
    }

    interface MultipleContent extends TargetedInterface {

        List<Record> getDefaultModules();

        default List<Record> getTargetedModules(HttpServletRequest request) {
            return generateModuleList(request, getDefaultModules());
        }
    }

    default List<Record> generateModuleList(HttpServletRequest request, Record module) {
        List<Record> modules = new ArrayList<>();
        modules.add(module);
        return generateModuleList(request, modules);
    }

    default List<Record> generateModuleList(HttpServletRequest request, List<Record> modules) {

        ObjectType objectType = ObjectType.getInstance(Record.class);

        Class objectClass = getContentType();
        if (!ObjectUtils.isBlank(objectClass)) {
            objectType = ObjectType.getInstance(objectClass);
        }
        return getRenderingMode().generateModuleList(request, modules, objectType);
    }
}