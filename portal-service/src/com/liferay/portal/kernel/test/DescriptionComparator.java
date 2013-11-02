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

package com.liferay.portal.kernel.test;

import java.util.Comparator;

import org.junit.runner.Description;

/**
 * @author Shuyang Zhou
 */
public class DescriptionComparator implements Comparator<Description> {

	@Override
	public int compare(Description description1, Description description2) {
		String displayName1 = description1.getDisplayName();
		String displayName2 = description2.getDisplayName();

		return displayName1.compareTo(displayName2);
	}

}