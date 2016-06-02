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

import java.lang.reflect.Field;

public class FieldWrapper<R> extends WrapperAbstract {

	private final Field field;

	public FieldWrapper(Field field) {
		this.field = field;
	}

	@Override
	public boolean exists() {
		return this.field != null;
	}

	public String getName() {
		return this.field.getName();
	}

	public R get(Object object) {
		try {
			return (R) this.field.get(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public R getSilent(Object object) {
		try {
			return (R) this.field.get(object);
		} catch (Exception e) {
		}
		return null;
	}

	public void set(Object object, R value) {
		try {
			this.field.set(object, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setSilent(Object object, R value) {
		try {
			this.field.set(object, value);
		} catch (Exception e) {
		}
	}

	public Field getField() {
		return field;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null || getClass() != object.getClass()) { return false; }

		FieldWrapper<?> that = (FieldWrapper<?>) object;

		if (field != null ? !field.equals(that.field) : that.field != null) { return false; }

		return true;
	}

	@Override
	public int hashCode() {
		return field != null ? field.hashCode() : 0;
	}
}
