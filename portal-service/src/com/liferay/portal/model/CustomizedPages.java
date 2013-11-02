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

package com.liferay.portal.model;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-15626.
 * </p>
 *
 * @author Raymond Augé
 */
public class CustomizedPages {

	public static String namespaceColumnId(String columnId) {
		return columnId.concat(_CUSTOMIZABLE_SUFFIX);
	}

	public static String namespacePlid(long plid) {
		return CustomizedPages.class.getName().concat(String.valueOf(plid));
	}

	private static final String _CUSTOMIZABLE_SUFFIX = "-customizable";

}