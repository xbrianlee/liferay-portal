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

package com.liferay.portal.kernel.search;

/**
 * @author Brian Wing Shun Chan
 */
public class DocumentComparatorOrderBy {

	public DocumentComparatorOrderBy(
		String name, boolean asc, boolean caseSensitive) {

		_name = name;
		_asc = asc;
		_caseSensitive = caseSensitive;
	}

	public String getName() {
		return _name;
	}

	public boolean isAsc() {
		return _asc;
	}

	public boolean isCaseSensitive() {
		return _caseSensitive;
	}

	private boolean _asc;
	private boolean _caseSensitive;
	private String _name;

}