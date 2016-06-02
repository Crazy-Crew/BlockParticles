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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Container class for resolver-queries Used by {@link MethodResolver}
 *
 * @see org.inventivetalent.reflection.resolver.ResolverQuery.Builder
 */
public class ResolverQuery {

	private String     name;
	private Class<?>[] types;

	public ResolverQuery(String name, Class<?>... types) {
		this.name = name;
		this.types = types;
	}

	public ResolverQuery(String name) {
		this.name = name;
		this.types = new Class[0];
	}

	public ResolverQuery(Class<?>... types) {
		this.types = types;
	}

	public String getName() {
		return name;
	}

	public Class<?>[] getTypes() {
		return types;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		ResolverQuery that = (ResolverQuery) o;

		if (name != null ? !name.equals(that.name) : that.name != null) { return false; }
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(types, that.types);

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (types != null ? Arrays.hashCode(types) : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ResolverQuery{" +
				"name='" + name + '\'' +
				", types=" + Arrays.toString(types) +
				'}';
	}

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder class for {@link ResolverQuery} Access using {@link ResolverQuery#builder()}
	 */
	public static class Builder {

		private List<ResolverQuery> queryList = new ArrayList<ResolverQuery>();

		private Builder() {
		}

		public Builder with(String name, Class<?>[] types) {
			queryList.add(new ResolverQuery(name, types));
			return this;
		}

		public Builder with(String name) {
			queryList.add(new ResolverQuery(name));
			return this;
		}

		public Builder with(Class<?>[] types) {
			queryList.add(new ResolverQuery(types));
			return this;
		}

		public ResolverQuery[] build() {
			return queryList.toArray(new ResolverQuery[queryList.size()]);
		}

	}
}
