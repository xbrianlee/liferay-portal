/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.kernel.javadoc;

/**
 * @author Igor Spasic
 */
public class JavadocClass extends BaseJavadoc {

	public JavadocClass(Class<?> clazz) {
		_clazz = clazz;
	}

	public String[] getAuthors() {
		return _authors;
	}

	public Class<?> getClazz() {
		return _clazz;
	}

	public void setAuthors(String[] authors) {
		_authors = authors;
	}

	public void setClazz(Class<?> clazz) {
		_clazz = clazz;
	}

	private String[] _authors;
	private Class<?> _clazz;

}