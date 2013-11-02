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

/**
 * @author Roberto Díaz
 */
public class SearchPaginationUtil {

	public static int[] calculateStartAndEnd(int cur, int delta) {
		int start = 0;

		if (cur > 0) {
			start = (cur - 1) * delta;
		}

		int end = start + delta;

		return new int[] {start, end};
	}

	public static int[] calculateStartAndEnd(int start, int end, int total) {
		if (total <= 0) {
			return new int[] {0, 0};
		}

		int[] startAndEnd = {start, end};

		int delta = end - start;

		if (delta < 0) {
			return new int[] {0, 0};
		}

		while ((start > 0) && (start >= total)) {
			int cur = start / delta;

			startAndEnd = calculateStartAndEnd(cur, delta);

			start = startAndEnd[0];
		}

		end = startAndEnd[1];

		if (end > total) {
			startAndEnd[1] = total;
		}

		return startAndEnd;
	}

}