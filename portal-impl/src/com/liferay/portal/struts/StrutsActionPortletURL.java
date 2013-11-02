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

package com.liferay.portal.struts;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Portlet;
import com.liferay.portlet.PortletResponseImpl;
import com.liferay.portlet.PortletURLImplWrapper;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class StrutsActionPortletURL extends PortletURLImplWrapper {

	public StrutsActionPortletURL(
		PortletResponseImpl portletResponseImpl, long plid, String lifecycle) {

		super(portletResponseImpl, plid, lifecycle);

		_portlet = portletResponseImpl.getPortlet();
		_strutsPath =
			StringPool.SLASH + _portlet.getStrutsPath() + StringPool.SLASH;
	}

	@Override
	public void setParameter(String name, String value) {
		if (name.equals("struts_action")) {
			if (!value.startsWith(_strutsPath)) {
				int pos = value.lastIndexOf(CharPool.SLASH);

				value = _strutsPath + value.substring(pos + 1);
			}
		}

		super.setParameter(name, value);
	}

	@Override
	public void setParameters(Map<String, String[]> params) {
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();

			if (name.equals("struts_action")) {
				for (int i = 0; i < values.length; i++) {
					String value = values[i];

					if (!value.startsWith(_strutsPath)) {
						int pos = value.lastIndexOf(CharPool.SLASH);

						value = _strutsPath + value.substring(pos + 1);

						values[i] = value;
					}
				}
			}
		}

		super.setParameters(params);
	}

	private Portlet _portlet;
	private String _strutsPath;

}