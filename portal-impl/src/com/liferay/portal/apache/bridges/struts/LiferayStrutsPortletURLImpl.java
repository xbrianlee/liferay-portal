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

package com.liferay.portal.apache.bridges.struts;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portlet.PortletResponseImpl;
import com.liferay.portlet.PortletURLImplWrapper;

import org.apache.portals.bridges.struts.StrutsPortletURL;

/**
 * @author Michael Young
 */
public class LiferayStrutsPortletURLImpl extends PortletURLImplWrapper {

	public LiferayStrutsPortletURLImpl(
		PortletResponseImpl portletResponseImpl, long plid, String lifecycle) {

		super(portletResponseImpl, plid, lifecycle);
	}

	@Override
	public void setParameter(String name, String value) {
		super.setParameter(name, value);

		// Add parameters from the query string because bridges passes these
		// through instead of setting them on the portlet URL

		String decodedValue = HttpUtil.decodeURL(value);

		try {
			if (name.equals(StrutsPortletURL.PAGE)) {
				String[] urlComponents = decodedValue.split("\\?", 2);

				if (urlComponents.length != 2) {
					return;
				}

				String[] nameValue = urlComponents[1].split("\\&");

				for (int i = 0; i < nameValue.length; i++) {
					String[] nameValuePair = nameValue[i].split("\\=", 2);

					if (nameValuePair.length == 2) {
						super.setParameter(nameValuePair[0], nameValuePair[1]);
					}
					else if (nameValuePair.length == 1) {
						super.setParameter(nameValuePair[0], "true");
					}
				}
			}
		}
		catch (Throwable t) {
			_log.error("Could not parse Struts page query string " + value, t);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayStrutsPortletURLImpl.class);

}