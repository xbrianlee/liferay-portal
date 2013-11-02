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

package com.liferay.portal.kernel.plugin;

import java.util.Comparator;

/**
 * @author Jorge Ferrer
 */
public class PluginPackageNameAndContextComparator
	implements Comparator<PluginPackage> {

	@Override
	public int compare(PluginPackage package1, PluginPackage package2) {
		String name1 = package1.getName();
		String name2 = package2.getName();

		int value = name1.compareTo(name2);

		if (value == 0) {
			String context1 = package1.getContext();
			String context2 = package2.getContext();

			value = context1.compareTo(context2);
		}

		return value;
	}

}