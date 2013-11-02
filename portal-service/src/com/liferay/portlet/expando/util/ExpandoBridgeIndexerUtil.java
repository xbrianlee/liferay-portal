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

package com.liferay.portlet.expando.util;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.expando.model.ExpandoBridge;

/**
 * @author Raymond Augé
 */
public class ExpandoBridgeIndexerUtil {

	public static void addAttributes(
		Document doc, ExpandoBridge expandoBridge) {

		getExpandoBridgeIndexer().addAttributes(doc, expandoBridge);
	}

	public static String encodeFieldName(String columnName) {
		return getExpandoBridgeIndexer().encodeFieldName(columnName);
	}

	public static ExpandoBridgeIndexer getExpandoBridgeIndexer() {
		PortalRuntimePermission.checkGetBeanProperty(
			ExpandoBridgeIndexerUtil.class);

		return _expandoBridgeIndexer;
	}

	public void setExpandoBridgeIndexer(
		ExpandoBridgeIndexer expandoBridgeIndexer) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_expandoBridgeIndexer = expandoBridgeIndexer;
	}

	private static ExpandoBridgeIndexer _expandoBridgeIndexer;

}