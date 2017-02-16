package com.klishgroup.targetting.util;

import com.klishgroup.targetting.Action;
import com.klishgroup.targetting.ExtendedAttributeValue;
import com.klishgroup.targetting.RenderingMode;
import com.klishgroup.targetting.Rule;
import com.klishgroup.targetting.TargetedInterface;
import com.psddev.cms.tool.ToolPageContext;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.State;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PreviewUtils {

    private static transient Logger LOGGER = Logger.getLogger(PreviewUtils.class);

    public static void renderTargettingRulesFields(ToolPageContext wp, Object object) throws IOException {

        Map<ExtendedAttributeValue, Set<String>> extendedAttributeValues = new HashMap<>();

        generateRuleElements(extendedAttributeValues, object);

        extendedAttributeValues.keySet().stream()
                .filter(Objects::nonNull)
                .forEach(extendedAttributeValue -> {
                    try {
                        wp.writeHtml(" ");

                        Set<String> values = extendedAttributeValues.get(extendedAttributeValue);

                        if (ObjectUtils.isBlank(values)) {
                            wp.writeElement("input",
                                    "type", "text",
                                    "style", "width: 10em",
                                    "name", extendedAttributeValue.getId().toString(),
                                    "placeholder", extendedAttributeValue.getLabel());
                        } else {
                            wp.writeStart("select",
                                    "name", extendedAttributeValue.getId().toString(),
                                    "onchange", "var $input = $(this)," +
                                            "$form = $input.closest('form');" +
                                            "$('iframe[name=\"' + $form.attr('target') + '\"]').css('width', $input.val() || '100%');" +
                                            "$form.submit();");

                            wp.writeStart("option",
                                    "value", "",
                                    "selected", "");
                            wp.writeHtml("Default");
                            wp.writeEnd();

                            values.stream()
                                    .filter(Objects::nonNull)
                                    .forEach(value -> {
                                        try {
                                            wp.writeStart("option", "value", value);
                                            wp.writeHtml(StringUtils.toLabel(value));
                                            wp.writeEnd();
                                        } catch (IOException e) {
                                           LOGGER.error(e);
                                        }
                                    });

                            wp.writeEnd();
                        }
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                });

        wp.writeHtml(" ");
        wp.writeStart("button", "type", "submit", "value", "Preview");
        wp.writeHtml("Preview");
        wp.writeEnd();
    }

    private static void generateRuleElements(Map<ExtendedAttributeValue, Set<String>> extendedAttributeValues, Object object) {

        if (object instanceof List) {
            for (Object l : (List) object) {
                generateRuleElements(extendedAttributeValues, l);
            }
        } else if (object instanceof Map) {

            Map<String, Object> map = (Map) object;

            if (map.containsKey("_ref")) {
                Object record = Query.fromAll().where("_id = ?", map.get("_ref")).first();

                if (!ObjectUtils.isBlank(record)) {
                    generateRuleElements(extendedAttributeValues, record);
                }
            } else {
                map.keySet()
                        .stream()
                        .filter(Objects::nonNull)
                        .filter(key -> !key.startsWith("cms."))
                        .forEach(key -> generateRuleElements(extendedAttributeValues, map.get(key)));
            }
        } else if (object instanceof Record) {
            Record record = (Record) object;

            if (record instanceof TargetedInterface) {
                RenderingMode renderingMode = record.as(TargetedInterface.Data.class).getRenderingMode();

                if (renderingMode instanceof RenderingMode.DynamicRendering) {
                    generateRuleElements(extendedAttributeValues, renderingMode.getState().getRawValues());
                }
            } else if (record instanceof Rule) {
                Action action = ((Rule) record).getAction();

                ExtendedAttributeValue extendedAttributeValue = action.getValue();
                Set<String> values = action.getValuesForPreviewSimulation();

                if (extendedAttributeValues.containsKey(extendedAttributeValue)) {
                    values.addAll(extendedAttributeValues.get(action.getValue()));
                }
                extendedAttributeValues.put(action.getValue(), values);
            } else {
                State state = State.getInstance(object);
                generateRuleElements(extendedAttributeValues, state.getRawValues());
            }
        }
    }
}
