package org.inventivetalent.reflection.resolver.wrapper;

import java.util.Objects;

public class ClassWrapper<R> extends WrapperAbstract {
    
    private final Class<R> clazz;
    
    public ClassWrapper(Class<R> clazz) {
        this.clazz = clazz;
    }
    
    @Override
    public boolean exists() {
        return this.clazz != null;
    }
    
    public Class<R> getClazz() {
        return clazz;
    }
    
    public String getName() {
        return this.clazz.getName();
    }
    
    public R newInstance() {
        try {
            return this.clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public R newInstanceSilent() {
        try {
            return this.clazz.newInstance();
        } catch (Exception e) {
        }
        return null;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        
        ClassWrapper<?> that = (ClassWrapper<?>) object;
        
        return Objects.equals(clazz, that.clazz);
        
    }
    
    @Override
    public int hashCode() {
        return clazz != null ? clazz.hashCode() : 0;
    }
    
}