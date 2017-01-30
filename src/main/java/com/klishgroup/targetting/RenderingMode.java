package com.klishgroup.targetting;


import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class RenderingMode extends Record {

    public abstract List<Record> generateModuleList(HttpServletRequest request, List<Record> modules, ObjectType contentType);

    public static class DynamicRendering extends RenderingMode {

        @Required
        @Minimum(1)
        private List<Rule> rules;

        public List<Rule> getRules() {
            if (ObjectUtils.isBlank(rules)) {
                return new ArrayList<>();
            }
            return rules;
        }

        public void setRules(List<Rule> rules) {
            this.rules = rules;
        }

        @Override
        public List<Record> generateModuleList(HttpServletRequest request, List<Record> modules, ObjectType contentType) {

            List<Record> dynamicModules = new ArrayList<>();
            for (Rule rule : getRules()) {
                dynamicModules = rule.execute(request, contentType);

                if (!ObjectUtils.isBlank(dynamicModules)) {
                    break;
                }
            }
            return (ObjectUtils.isBlank(dynamicModules)) ? modules : dynamicModules;
        }
    }

    public static class StaticRendering extends RenderingMode {

        @Override
        public List<Record> generateModuleList(HttpServletRequest request, List<Record> modules, ObjectType contentType) {
            return modules;
        }
    }
}
