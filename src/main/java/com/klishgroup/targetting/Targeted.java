package com.klishgroup.targetting;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class Targeted extends Record {

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
            return generateModuleList(request, module);
        }
    }

    public static class MultipleContent extends Targeted {
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
            return generateModuleList(request, modules);
        }
    }

    public List<Record> generateModuleList(HttpServletRequest request, Record module) {
        List<Record> modules = new ArrayList<>();
        modules.add(module);
        return generateModuleList(request, modules);
    }

    public List<Record> generateModuleList(HttpServletRequest request, List<Record> modules) {
        return renderingMode.generateModuleList(request, modules);
    }
}
