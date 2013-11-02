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
public interface BooleanClauseOccur {

	public static final BooleanClauseOccur MUST = new BooleanClauseOccurImpl(
		"MUST");

	public static final BooleanClauseOccur MUST_NOT =
		new BooleanClauseOccurImpl("MUST_NOT");

	public static final BooleanClauseOccur SHOULD = new BooleanClauseOccurImpl(
		"SHOULD");

	public String getName();

}