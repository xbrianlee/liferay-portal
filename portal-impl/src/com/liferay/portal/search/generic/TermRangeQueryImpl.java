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

package com.liferay.portal.search.generic;

import com.liferay.portal.kernel.search.BaseQueryImpl;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Raymond Augé
 */
public class TermRangeQueryImpl extends BaseQueryImpl
	implements TermRangeQuery {

	public TermRangeQueryImpl(
		String field, String lowerTerm, String upperTerm, boolean includesLower,
		boolean includesUpper) {

		_field = field;
		_lowerTerm = lowerTerm;
		_upperTerm = upperTerm;
		_includesLower = includesLower;
		_includesUpper = includesUpper;
	}

	@Override
	public String getField() {
		return _field;
	}

	@Override
	public String getLowerTerm() {
		return _lowerTerm;
	}

	@Override
	public String getUpperTerm() {
		return _upperTerm;
	}

	@Override
	public Object getWrappedQuery() {
		return this;
	}

	@Override
	public boolean includesLower() {
		return _includesLower;
	}

	@Override
	public boolean includesUpper() {
		return _includesUpper;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append(_field);
		sb.append(CharPool.COLON);

		if (_includesLower) {
			sb.append(CharPool.OPEN_BRACKET);
		}
		else {
			sb.append(CharPool.OPEN_CURLY_BRACE);
		}

		if (_lowerTerm != null) {
			sb.append(_lowerTerm);
		}
		else {
			sb.append(CharPool.STAR);
		}

		sb.append(" TO ");

		if (_upperTerm != null) {
			sb.append(_upperTerm);
		}
		else {
			sb.append(CharPool.STAR);
		}

		if (_includesUpper) {
			sb.append(CharPool.CLOSE_BRACKET);
		}
		else {
			sb.append(CharPool.CLOSE_CURLY_BRACE);
		}

		return sb.toString();
	}

	private String _field;
	private boolean _includesLower;
	private boolean _includesUpper;
	private String _lowerTerm;
	private String _upperTerm;

}