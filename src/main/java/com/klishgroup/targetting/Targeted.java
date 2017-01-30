package com.klishgroup.targetting;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.DatabaseEnvironment;
import com.psddev.dari.db.ObjectField;
import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class Targeted extends Record {

    @Ignored
    private Class targetedType;

    @ToolUi.ReadOnly
    private ObjectType contentType;

    public Targeted() {
    }

    public Targeted(Class targetedType) {
        this.targetedType = targetedType;
    }

    public Class getTargetedType() {
        return targetedType;
    }

    public void setTargetedType(Class targetedType) {
        this.targetedType = targetedType;
    }

    public ObjectType getContentType() {
        return contentType;
    }

    public void setContentType(ObjectType contentType) {
        this.contentType = contentType;
    }

    @Required
    @Embedded
    private RenderingMode renderingMode;

    public RenderingMode getRenderingMode() {
        return renderingMode;
    }

    public void setRenderingMode(RenderingMode renderingMode) {
        this.renderingMode = renderingMode;
    }

    public abstract List<Record> getModules(HttpServletRequest request);

    public static class SingleContent extends Targeted {

        public SingleContent() {
        }

        public SingleContent(Class classType) {
            super(classType);
            setFieldType("module");
        }

        @Required
        @ToolUi.Note("Default Content")
        private Record module;

        public Record getModule() {
            return module;
        }

        public void setModule(Record module) {
            this.module = module;
        }

        @Override
        public List<Record> getModules(HttpServletRequest request) {
            return generateModuleList(request, getModule());
        }
    }

    public static class MultipleContent extends Targeted {

        public MultipleContent() {
        }

        public MultipleContent(Class classType) {
            super(classType);
            setFieldType("modules");
        }

        @Required
        @ToolUi.Note("Default Content")
        @Minimum(1)
        private List<Record> modules;

        public List<Record> getModules() {
            if (ObjectUtils.isBlank(modules)) {
                return new ArrayList<>();
            }
            return modules;
        }

        public void setModules(List<Record> modules) {
            this.modules = modules;
        }

        @Override
        public List<Record> getModules(HttpServletRequest request) {
            return generateModuleList(request, getModules());
        }
    }

    protected List<Record> generateModuleList(HttpServletRequest request, Record module) {
        List<Record> modules = new ArrayList<>();
        modules.add(module);
        return generateModuleList(request, modules);
    }

    protected List<Record> generateModuleList(HttpServletRequest request, List<Record> modules) {
        return renderingMode.generateModuleList(request, modules, getContentType());
    }

    protected void setFieldType(String fieldName) {

        ObjectField field = this.getState().getField(fieldName);
        DatabaseEnvironment environment = field.getParent().getEnvironment();
        ObjectType type = environment.getTypeByClass(getTargetedType());

        if (!ObjectUtils.isBlank(type)) {
            LinkedHashSet types = new LinkedHashSet();
            types.add(type);
            field.setTypes(types);
            this.contentType = type;
        } else {
            this.contentType = environment.getTypeByClass(Record.class);
        }
    }
}
