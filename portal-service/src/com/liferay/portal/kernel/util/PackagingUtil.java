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

package com.liferay.portal.kernel.util;

import java.io.File;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author Raymond Augé
 */
public class PackagingUtil {

	public static List<String> getPackagesFromPath(File file) {
		Set<String> packages = new HashSet<String>();
		Stack<String> packageStack = new Stack<String>();

		subPackages(file, packages, packageStack);

		List<String> list = ListUtil.fromCollection(packages);

		Collections.sort(list);

		return list;
	}

	protected static void subPackages(
		File file, Set<String> packages, Stack<String> packageStack) {

		for (File subFile : file.listFiles()) {
			if (subFile.isDirectory()) {
				packageStack.push(subFile.getName());

				String packageName = StringUtil.merge(
					packageStack, StringPool.PERIOD);

				if (packageName.contains(StringPool.PERIOD)) {
					packages.add(packageName);
				}

				subPackages(subFile, packages, packageStack);

				packageStack.pop();
			}
		}
	}

}