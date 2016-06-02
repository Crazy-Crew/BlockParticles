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

package org.inventivetalent.reflection.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Helper class to set fields, methods &amp; constructors accessible
 */
public abstract class AccessUtil {

	/**
	 * Sets the field accessible and removes final modifiers
	 *
	 * @param field Field to set accessible
	 * @return the Field
	 * @throws ReflectiveOperationException  (usually never)
	 */
	public static Field setAccessible(Field field) throws ReflectiveOperationException {
		field.setAccessible(true);
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
		return field;
	}

	/**
	 * Sets the method accessible
	 *
	 * @param method Method to set accessible
	 * @return the Method
	 * @throws ReflectiveOperationException  (usually never)
	 */
	public static Method setAccessible(Method method) throws ReflectiveOperationException {
		method.setAccessible(true);
		return method;
	}

	/**
	 * Sets the constructor accessible
	 *
	 * @param constructor Constructor to set accessible
	 * @return the Constructor
	 * @throws ReflectiveOperationException  (usually never)
	 */
	public static Constructor setAccessible(Constructor constructor) throws ReflectiveOperationException {
		constructor.setAccessible(true);
		return constructor;
	}

}
