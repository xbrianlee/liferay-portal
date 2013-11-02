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

import java.util.Comparator;

/**
 * @author Jorge Ferrer
 * @author Minhchau Dang
 * @author Brian Wing Shun Chan
 */
public class PortletControlPanelWeightComparator
	implements Comparator<Portlet> {

	@Override
	public int compare(Portlet portlet1, Portlet portlet2) {
		double portletWeight1 = portlet1.getControlPanelEntryWeight();
		double portletWeight2 = portlet2.getControlPanelEntryWeight();

		int value = Double.compare(portletWeight1, portletWeight2);

		if (value != 0) {
			return value;
		}

		String portletId1 = portlet1.getPortletId();
		String portletId2 = portlet2.getPortletId();

		return portletId1.compareTo(portletId2);
	}

}