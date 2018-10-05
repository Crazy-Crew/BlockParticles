package org.inventivetalent.reflection.annotation;

import org.inventivetalent.reflection.minecraft.Minecraft;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Resolves the annotated {@link org.inventivetalent.reflection.resolver.wrapper.MethodWrapper} or {@link java.lang.reflect.Method} field to the first matching method name.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Method {

    /**
     * Name of the class to load this method from
     *
     * @return name of the class
     */
    String className();

    /**
     * Possible names of the method. Use <code>&gt;</code> or <code>&lt;</code> as a name prefix in combination with {@link #versions()} to specify versions newer- or older-than.
     *
     * @return method names
     */
    String[] value();

    /**
     * Specific versions for the names.
     *
     * @return Array of versions for the class names
     */
    Minecraft.Version[] versions() default {};

    /**
     * Whether to ignore any reflection exceptions thrown. Defaults to <code>true</code>
     *
     * @return whether to ignore exceptions
     */
    boolean ignoreExceptions() default true;
}