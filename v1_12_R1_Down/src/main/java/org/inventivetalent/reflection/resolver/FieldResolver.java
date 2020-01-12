package org.inventivetalent.reflection.resolver;

import org.inventivetalent.reflection.resolver.wrapper.FieldWrapper;
import org.inventivetalent.reflection.util.AccessUtil;

import java.lang.reflect.Field;

/**
 * Resolver for fields
 */
public class FieldResolver extends MemberResolver<Field> {
    
    public FieldResolver(Class<?> clazz) {
        super(clazz);
    }
    
    public FieldResolver(String className) throws ClassNotFoundException {
        super(className);
    }
    
    @Override
    public Field resolveIndex(int index) throws IndexOutOfBoundsException, ReflectiveOperationException {
        return AccessUtil.setAccessible(this.clazz.getDeclaredFields()[index]);
    }
    
    @Override
    public Field resolveIndexSilent(int index) {
        try {
            return resolveIndex(index);
        } catch (IndexOutOfBoundsException | ReflectiveOperationException ignored) {
        }
        return null;
    }
    
    @Override
    public FieldWrapper resolveIndexWrapper(int index) {
        return new FieldWrapper<>(resolveIndexSilent(index));
    }
    
    public FieldWrapper resolveWrapper(String... names) {
        return new FieldWrapper<>(resolveSilent(names));
    }
    
    public Field resolveSilent(String... names) {
        try {
            return resolve(names);
        } catch (Exception e) {
        }
        return null;
    }
    
    public Field resolve(String... names) throws NoSuchFieldException {
        ResolverQuery.Builder builder = ResolverQuery.builder();
        for (String name : names)
            builder.with(name);
        try {
            return super.resolve(builder.build());
        } catch (ReflectiveOperationException e) {
            throw (NoSuchFieldException) e;
        }
    }
    
    public Field resolveSilent(ResolverQuery... queries) {
        try {
            return resolve(queries);
        } catch (Exception e) {
        }
        return null;
    }
    
    public Field resolve(ResolverQuery... queries) throws NoSuchFieldException {
        try {
            return super.resolve(queries);
        } catch (ReflectiveOperationException e) {
            throw (NoSuchFieldException) e;
        }
    }
    
    @Override
    protected Field resolveObject(ResolverQuery query) throws ReflectiveOperationException {
        if (query.getTypes() == null || query.getTypes().length == 0) {
            return AccessUtil.setAccessible(this.clazz.getDeclaredField(query.getName()));
        } else {
            for (Field field : this.clazz.getDeclaredFields()) {
                if (field.getName().equals(query.getName())) {
                    for (Class type : query.getTypes()) {
                        if (field.getType().equals(type)) {
                            return field;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Attempts to find the first field of the specified type
     *
     * @param type Type to find
     * @return the Field
     * @throws ReflectiveOperationException (usually never)
     * @see #resolveByLastType(Class)
     */
    public Field resolveByFirstType(Class<?> type) throws ReflectiveOperationException {
        for (Field field : this.clazz.getDeclaredFields()) {
            if (field.getType().equals(type)) {
                return AccessUtil.setAccessible(field);
            }
        }
        throw new NoSuchFieldException("Could not resolve field of type '" + type.toString() + "' in class " + this.clazz);
    }
    
    /**
     * Attempts to find the first field of the specified type
     *
     * @param type Type to find
     * @return the Field
     * @see #resolveByLastTypeSilent(Class)
     */
    public Field resolveByFirstTypeSilent(Class<?> type) {
        try {
            return resolveByFirstType(type);
        } catch (Exception e) {
        }
        return null;
    }
    
    /**
     * Attempts to find the last field of the specified type
     *
     * @param type Type to find
     * @return the Field
     * @throws ReflectiveOperationException (usually never)
     * @see #resolveByFirstType(Class)
     */
    public Field resolveByLastType(Class<?> type) throws ReflectiveOperationException {
        Field field = null;
        for (Field field1 : this.clazz.getDeclaredFields()) {
            if (field1.getType().equals(type)) {
                field = field1;
            }
        }
        if (field == null) {
            throw new NoSuchFieldException("Could not resolve field of type '" + type.toString() + "' in class " + this.clazz);
        }
        return AccessUtil.setAccessible(field);
    }
    
    public Field resolveByLastTypeSilent(Class<?> type) {
        try {
            return resolveByLastType(type);
        } catch (Exception e) {
        }
        return null;
    }
    
    @Override
    protected NoSuchFieldException notFoundException(String joinedNames) {
        return new NoSuchFieldException("Could not resolve field for " + joinedNames + " in class " + this.clazz);
    }
    
}