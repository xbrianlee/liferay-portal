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

import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.TermRangeQueryFactory;

/**
 * @author Raymond Augé
 */
public class TermRangeQueryFactoryImpl implements TermRangeQueryFactory {

	@Override
	public TermRangeQuery create(
		String field, String lowerTerm, String upperTerm, boolean includesLower,
		boolean includesUpper) {

		return new TermRangeQueryImpl(
			field, lowerTerm, upperTerm, includesLower, includesUpper);
	}

}