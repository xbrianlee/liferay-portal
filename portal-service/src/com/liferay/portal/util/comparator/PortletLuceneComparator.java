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

package com.liferay.portal.util.comparator;

import com.liferay.portal.model.Portlet;
import com.liferay.portal.util.PortletKeys;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletLuceneComparator
	implements Comparator<Portlet>, Serializable {

	@Override
	public int compare(Portlet portlet1, Portlet portlet2) {
		String portletId1 = portlet1.getPortletId();
		String portletId2 = portlet2.getPortletId();

		// Index document library last because it's usually the slowest.

		if (portletId1.equals(PortletKeys.DOCUMENT_LIBRARY)) {
			return 1;
		}
		else if (portletId2.equals(PortletKeys.DOCUMENT_LIBRARY)) {
			return -1;
		}
		else {
			return portletId1.compareTo(portletId2);
		}
	}

}