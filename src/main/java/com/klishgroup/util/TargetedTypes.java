package com.klishgroup.util;

import com.psddev.dari.db.ObjectField;
import com.psddev.dari.db.ObjectType;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@ObjectField.AnnotationProcessorClass(TargetedTypesProcessor.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TargetedTypes {
}

class TargetedTypesProcessor implements ObjectField.AnnotationProcessor<Annotation> {
    TargetedTypesProcessor() {
    }

    public void process(ObjectType type, ObjectField field, Annotation annotation) {
    }
}