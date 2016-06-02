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

package org.inventivetalent.reflection.resolver.wrapper;

import java.lang.reflect.Constructor;

public class ConstructorWrapper<R> extends WrapperAbstract {

	private final Constructor<R> constructor;

	public ConstructorWrapper(Constructor<R> constructor) {
		this.constructor = constructor;
	}

	@Override
	public boolean exists() {
		return this.constructor != null;
	}

	public R newInstance(Object... args) {
		try {
			return this.constructor.newInstance(args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public R newInstanceSilent(Object... args) {
		try {
			return this.constructor.newInstance(args);
		} catch (Exception e) {
		}
		return null;
	}

	public Class<?>[] getParameterTypes() {
		return this.constructor.getParameterTypes();
	}

	public Constructor<R> getConstructor() {
		return constructor;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null || getClass() != object.getClass()) { return false; }

		ConstructorWrapper<?> that = (ConstructorWrapper<?>) object;

		return constructor != null ? constructor.equals(that.constructor) : that.constructor == null;

	}

	@Override
	public int hashCode() {
		return constructor != null ? constructor.hashCode() : 0;
	}
}
