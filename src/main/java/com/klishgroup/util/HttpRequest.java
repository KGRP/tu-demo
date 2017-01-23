package com.klishgroup.util;

import com.psddev.cms.view.servlet.ServletViewRequestAnnotationProcessor;
import com.psddev.cms.view.servlet.ServletViewRequestAnnotationProcessorClass;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Populates a field with the value of the request URL from an HTTP request.
 */
@ServletViewRequestAnnotationProcessorClass(HttpRequestProcessor.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HttpRequest {
}

class HttpRequestProcessor implements ServletViewRequestAnnotationProcessor<HttpRequest> {
    @Override
    public Object getValue(HttpServletRequest request, String fieldName, HttpRequest annotation) {
        return request;
    }
}
