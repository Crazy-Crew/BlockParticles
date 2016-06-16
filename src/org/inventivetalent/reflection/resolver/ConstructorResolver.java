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
		} catch (IndexOutOfBoundsException | ReflectiveOperationException ignored) {
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
		} catch (Exception e) {
		}
		return null;
	}

	public Constructor resolve(Class<?>[]... types) throws NoSuchMethodException {
		ResolverQuery.Builder builder = ResolverQuery.builder();
		for (Class<?>[] type : types)
			builder.with(type);
		try {
			return super.resolve(builder.build());
		} catch (ReflectiveOperationException e) {
			throw (NoSuchMethodException) e;
		}
	}

	@Override
	protected Constructor resolveObject(ResolverQuery query) throws ReflectiveOperationException {
		return AccessUtil.setAccessible(this.clazz.getDeclaredConstructor(query.getTypes()));
	}

	public Constructor resolveFirstConstructor() throws ReflectiveOperationException {
		for (Constructor constructor : this.clazz.getDeclaredConstructors()) {
			return AccessUtil.setAccessible(constructor);
		}
		return null;
	}

	public Constructor resolveFirstConstructorSilent() {
		try {
			return resolveFirstConstructor();
		} catch (Exception e) {
		}
		return null;
	}

	public Constructor resolveLastConstructor() throws ReflectiveOperationException {
		Constructor constructor = null;
		for (Constructor constructor1 : this.clazz.getDeclaredConstructors()) {
			constructor = constructor1;
		}
		if (constructor != null) { return AccessUtil.setAccessible(constructor); }
		return null;
	}

	public Constructor resolveLastConstructorSilent() {
		try {
			return resolveLastConstructor();
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	protected NoSuchMethodException notFoundException(String joinedNames) {
		return new NoSuchMethodException("Could not resolve constructor for " + joinedNames + " in class " + this.clazz);
	}
}
