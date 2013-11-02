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
public class BooleanClauseOccurImpl implements BooleanClauseOccur {

	public BooleanClauseOccurImpl(String name) {
		_name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BooleanClauseOccur)) {
			return false;
		}

		BooleanClauseOccur booleanClauseOccur = (BooleanClauseOccur)obj;

		String name = booleanClauseOccur.getName();

		if (_name.equals(name)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	private String _name;

}