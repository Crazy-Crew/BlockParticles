package org.inventivetalent.reflection.resolver;

import org.inventivetalent.reflection.resolver.wrapper.ConstructorWrapper;
import org.inventivetalent.reflection.util.AccessUtil;

import java.lang.reflect.Constructor;

/**
 * Resolver for constructors
 */
public class ConstructorResolver extends MemberResolver<Constructor> {
	
	public ConstructorResolver(Class<?> clazz) {
		super(clazz);
	}
	
	public ConstructorResolver(String className) throws ClassNotFoundException {
		super(className);
	}
	
	@Override
	public Constructor resolveIndex(int index) throws IndexOutOfBoundsException, ReflectiveOperationException {
		return AccessUtil.setAccessible(this.clazz.getDeclaredConstructors()[index]);
	}
	
	@Override
	public Constructor resolveIndexSilent(int index) {
		try {
			return resolveIndex(index);
		}catch(IndexOutOfBoundsException | ReflectiveOperationException ignored) {
		}
		return null;
	}
	
	@Override
	public ConstructorWrapper resolveIndexWrapper(int index) {
		return new ConstructorWrapper<>(resolveIndexSilent(index));
	}
	
	public ConstructorWrapper resolveWrapper(Class<?>[]... types) {
		return new ConstructorWrapper<>(resolveSilent(types));
	}
	
	public Constructor resolveSilent(Class<?>[]... types) {
		try {
			return resolve(types);
		}catch(Exception e) {
		}
		return null;
	}
	
	public Constructor resolve(Class<?>[]... types) throws NoSuchMethodException {
		ResolverQuery.Builder builder = ResolverQuery.builder();
		for(Class<?>[] type : types)
			builder.with(type);
		try {
			return super.resolve(builder.build());
		}catch(ReflectiveOperationException e) {
			throw (NoSuchMethodException) e;
		}
	}
	
	@Override
	protected Constructor resolveObject(ResolverQuery query) throws ReflectiveOperationException {
		return AccessUtil.setAccessible(this.clazz.getDeclaredConstructor(query.getTypes()));
	}
	
	public Constructor resolveFirstConstructor() throws ReflectiveOperationException {
		for(Constructor constructor : this.clazz.getDeclaredConstructors()) {
			return AccessUtil.setAccessible(constructor);
		}
		return null;
	}
	
	public Constructor resolveFirstConstructorSilent() {
		try {
			return resolveFirstConstructor();
		}catch(Exception e) {
		}
		return null;
	}
	
	public Constructor resolveLastConstructor() throws ReflectiveOperationException {
		Constructor constructor = null;
		for(Constructor constructor1 : this.clazz.getDeclaredConstructors()) {
			constructor = constructor1;
		}
		if(constructor != null) {
			return AccessUtil.setAccessible(constructor);
		}
		return null;
	}
	
	public Constructor resolveLastConstructorSilent() {
		try {
			return resolveLastConstructor();
		}catch(Exception e) {
		}
		return null;
	}
	
	@Override
	protected NoSuchMethodException notFoundException(String joinedNames) {
		return new NoSuchMethodException("Could not resolve constructor for " + joinedNames + " in class " + this.clazz);
	}
	
}