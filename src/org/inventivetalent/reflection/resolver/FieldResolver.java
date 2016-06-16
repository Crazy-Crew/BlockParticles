/*
 * Copyright 2016 inventivetalent. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and contributors and should not be interpreted as representing official policies,
 *  either expressed or implied, of anybody else.
 */

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
		if (field == null) { throw new NoSuchFieldException("Could not resolve field of type '" + type.toString() + "' in class " + this.clazz); }
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
