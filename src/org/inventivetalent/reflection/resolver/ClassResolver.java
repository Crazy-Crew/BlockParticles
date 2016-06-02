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

import org.inventivetalent.reflection.resolver.wrapper.ClassWrapper;

/**
 * Default {@link ClassResolver}
 */
public class ClassResolver extends ResolverAbstract<Class> {

	public ClassWrapper resolveWrapper(String... names) {
		return new ClassWrapper<>(resolveSilent(names));
	}

	public Class resolveSilent(String... names) {
		try {
			return resolve(names);
		} catch (Exception e) {
		}
		return null;
	}

	public Class resolve(String... names) throws ClassNotFoundException {
		ResolverQuery.Builder builder = ResolverQuery.builder();
		for (String name : names)
			builder.with(name);
		try {
			return super.resolve(builder.build());
		} catch (ReflectiveOperationException e) {
			throw (ClassNotFoundException) e;
		}
	}

	@Override
	protected Class resolveObject(ResolverQuery query) throws ReflectiveOperationException {
		return Class.forName(query.getName());
	}

	@Override
	protected ClassNotFoundException notFoundException(String joinedNames) {
		return new ClassNotFoundException("Could not resolve class for " + joinedNames);
	}
}
