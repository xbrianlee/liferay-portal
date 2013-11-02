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

package com.liferay.portal.kernel.dao.search;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class SearchPaginationUtilTest {

	@Test
	public void testCalculateStartAndEndWhenEmptyResultsPage() {
		int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(
			40, 60, 10);

		Assert.assertEquals(0, startAndEnd[0]);
		Assert.assertEquals(10, startAndEnd[1]);
	}

	@Test
	public void testCalculateStartAndEndWhenFullResultsPage() {
		int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(
			20, 40, 20);

		Assert.assertEquals(0, startAndEnd[0]);
		Assert.assertEquals(20, startAndEnd[1]);
	}

	@Test
	public void testCalculateStartAndEndWhenNoResults() {
		int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(
			20, 40, 0);

		Assert.assertEquals(0, startAndEnd[0]);
		Assert.assertEquals(0, startAndEnd[1]);
	}

	@Test
	public void testCalculateStartAndEndWhenResultsPage() {
		int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(
			20, 40, 80);

		Assert.assertEquals(20, startAndEnd[0]);
		Assert.assertEquals(40, startAndEnd[1]);
	}

	@Test
	public void testNotCalculateStartAndEndWhenNoResultsAndInitialPage() {
		int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(0, 20, 0);

		Assert.assertEquals(0, startAndEnd[0]);
		Assert.assertEquals(0, startAndEnd[1]);
	}

}