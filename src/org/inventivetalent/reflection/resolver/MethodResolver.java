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

import org.inventivetalent.reflection.resolver.wrapper.MethodWrapper;
import org.inventivetalent.reflection.util.AccessUtil;

import java.lang.reflect.Method;

/**
 * Resolver for methods
 */
public class MethodResolver extends MemberResolver<Method> {

	public MethodResolver(Class<?> clazz) {
		super(clazz);
	}

	public MethodResolver(String className) throws ClassNotFoundException {
		super(className);
	}

	@Override
	public Method resolveIndex(int index) throws IndexOutOfBoundsException, ReflectiveOperationException {
		return AccessUtil.setAccessible(this.clazz.getDeclaredMethods()[index]);
	}

	@Override
	public Method resolveIndexSilent(int index) {
		try {
			return resolveIndex(index);
		} catch (IndexOutOfBoundsException | ReflectiveOperationException ignored) {
		}
		return null;
	}

	@Override
	public MethodWrapper resolveIndexWrapper(int index) {
		return new MethodWrapper<>(resolveIndexSilent(index));
	}

	public MethodWrapper resolveWrapper(String... names) {
		return new MethodWrapper<>(resolveSilent(names));
	}

	public MethodWrapper resolveWrapper(ResolverQuery... queries) {
		return new MethodWrapper<>(resolveSilent(queries));
	}

	public Method resolveSilent(String... names) {
		try {
			return resolve(names);
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Method resolveSilent(ResolverQuery... queries) {
		return super.resolveSilent(queries);
	}

	public Method resolve(String... names) throws NoSuchMethodException {
		ResolverQuery.Builder builder = ResolverQuery.builder();
		for (String name : names) {
			builder.with(name);
		}
		return resolve(builder.build());
	}

	@Override
	public Method resolve(ResolverQuery... queries) throws NoSuchMethodException {
		try {
			return super.resolve(queries);
		} catch (ReflectiveOperationException e) {
			throw (NoSuchMethodException) e;
		}
	}

	@Override
	protected Method resolveObject(ResolverQuery query) throws ReflectiveOperationException {
		for (Method method : this.clazz.getDeclaredMethods()) {
			if (method.getName().equals(query.getName()) && (query.getTypes().length == 0 || ClassListEqual(query.getTypes(), method.getParameterTypes()))) {
				return AccessUtil.setAccessible(method);
			}
		}
		throw new NoSuchMethodException();
	}

	@Override
	protected NoSuchMethodException notFoundException(String joinedNames) {
		return new NoSuchMethodException("Could not resolve method for " + joinedNames + " in class " + this.clazz);
	}

	static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
		boolean equal = true;
		if (l1.length != l2.length) { return false; }
		for (int i = 0; i < l1.length; i++) {
			if (l1[i] != l2[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
}
