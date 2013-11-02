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

package com.liferay.portlet.asset.model;

/**
 * @author Jorge Ferrer
 * @author Juan Fernández
 */
public class AssetLinkConstants {

	public static final int TYPE_CHILD = 1;

	public static final int TYPE_RELATED = 0;

	public static boolean isTypeBi(int type) {
		return !isTypeUni(type);
	}

	public static boolean isTypeUni(int type) {
		if (type == TYPE_CHILD) {
			return true;
		}
		else {
			return false;
		}
	}

}