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

package com.liferay.portal.tools.comparator;

import com.thoughtworks.qdox.model.JavaMethod;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class JavaMethodComparator implements Comparator<JavaMethod> {

	@Override
	public int compare(JavaMethod method1, JavaMethod method2) {
		String name1 = method1.getName();
		String name2 = method2.getName();

		return name1.compareTo(name2);
	}

}