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

package com.liferay.portal.tools.sourceformatter;

/**
 * @author Carlos Sierra Andrés
 */
public class ImportPackage implements Comparable<ImportPackage> {

	@Override
	public int compareTo(ImportPackage importPackage) {
		return _import.compareTo(importPackage._import);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportPackage)) {
			return false;
		}

		ImportPackage importPackage = (ImportPackage)obj;

		return _import.equals(importPackage._import);
	}

	public String getImport() {
		return _import;
	}

	public String getLine() {
		return _line;
	}

	@Override
	public int hashCode() {
		return _import.hashCode();
	}

	protected ImportPackage(String importString, String line) {
		_import = importString;
		_line = line;
	}

	private String _import;
	private String _line;

}