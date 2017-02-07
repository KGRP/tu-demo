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
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PreviewUtils {

    private static transient Logger LOGGER = Logger.getLogger(PreviewUtils.class);

    public static void renderTargettingRulesFields(ToolPageContext wp, Object object) throws IOException {

        Set<ExtendedAttributeValue> extendedAttributeValues = new HashSet<>();

        generateRuleElements(extendedAttributeValues, object);

        extendedAttributeValues.stream()
                .filter(Objects::nonNull)
                .forEach(extendedAttributeValue -> {
                    try {
                        wp.writeHtml(" ");
                        wp.writeElement("input",
                                "type", "text",
                                "style", "width: 10em",
                                "name", extendedAttributeValue.getId().toString(),
                                "placeholder", extendedAttributeValue.getLabel());
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                });

        wp.writeHtml(" ");
        wp.writeStart("button", "type", "submit", "value", "Preview");
        wp.writeHtml("Preview");
        wp.writeEnd();
    }

    private static void generateRuleElements(Set<ExtendedAttributeValue> extendedAttributeValues, Object object) {

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
                generateRuleElements(extendedAttributeValues, action.getState().getRawValues());
            } else if (record instanceof ExtendedAttributeValue) {
                extendedAttributeValues.add((ExtendedAttributeValue) record);
            } else {
                State state = State.getInstance(object);
                generateRuleElements(extendedAttributeValues, state.getRawValues());
            }
        }
    }
}
