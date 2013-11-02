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

package com.liferay.portal.layoutconfiguration.util.xml;

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.RenderResponseImpl;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionURLLogic extends RuntimeLogic {

	public static final String CLOSE_1_TAG = "</runtime-action-url>";

	public static final String CLOSE_2_TAG = "/>";

	public static final String OPEN_TAG = "<runtime-action-url";

	public ActionURLLogic(RenderResponse renderResponse) {
		_renderResponseImpl = (RenderResponseImpl)renderResponse;
	}

	@Override
	public String getClose1Tag() {
		return CLOSE_1_TAG;
	}

	public String getLifecycle() {
		return _lifecycle;
	}

	@Override
	public String getOpenTag() {
		return OPEN_TAG;
	}

	@Override
	public String processXML(String xml) throws Exception {
		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		LiferayPortletURL liferayPortletURL =
			_renderResponseImpl.createLiferayPortletURL(getLifecycle());

		String portletId = root.attributeValue("portlet-name");

		if (portletId != null) {
			portletId = PortalUtil.getJsSafePortletId(portletId);

			liferayPortletURL.setPortletId(portletId);
		}

		for (int i = 1;; i++) {
			String paramName = root.attributeValue("param-name-" + i);
			String paramValue = root.attributeValue("param-value-" + i);

			if ((paramName == null) || (paramValue == null)) {
				break;
			}

			liferayPortletURL.setParameter(paramName, paramValue);
		}

		return liferayPortletURL.toString();
	}

	private String _lifecycle = PortletRequest.ACTION_PHASE;
	private RenderResponseImpl _renderResponseImpl;

}