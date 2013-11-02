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

package com.liferay.portal.kernel.repository.cmis.search;

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Mika Koivisto
 */
public class CMISBetweenExpression implements CMISCriterion {

	public CMISBetweenExpression(
		String field, String lowerTerm, String upperTerm, boolean includesLower,
		boolean includesUpper) {

		_field = field;
		_lowerTerm = lowerTerm;
		_upperTerm = upperTerm;
		_includesLower = includesLower;
		_includesUpper = includesUpper;
	}

	@Override
	public String toQueryFragment() {
		StringBundler sb = new StringBundler(7);

		sb.append(_field);

		if (_includesLower) {
			sb.append(" >= ");
		}
		else {
			sb.append(" > ");
		}

		sb.append(_lowerTerm);
		sb.append(" AND ");
		sb.append(_field);

		if (_includesUpper) {
			sb.append(" <= ");
		}
		else {
			sb.append(" < ");
		}

		sb.append(_upperTerm);

		return sb.toString();
	}

	private String _field;
	private boolean _includesLower;
	private boolean _includesUpper;
	private String _lowerTerm;
	private String _upperTerm;

}