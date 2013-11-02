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

package com.liferay.portal.template;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletPreferencesImpl;

import javax.portlet.ReadOnlyException;

/**
 * @author Brian Wing Shun Chan
 */
public class TemplatePortletPreferences {

	public TemplatePortletPreferences() {
		_portletPreferencesImpl = new PortletPreferencesImpl();
	}

	public void reset() {
		_portletPreferencesImpl.reset();
	}

	public void setValue(String key, String value) throws ReadOnlyException {
		_portletPreferencesImpl.setValue(key, value);
	}

	public void setValues(String key, String[] values)
		throws ReadOnlyException {

		_portletPreferencesImpl.setValues(key, values);
	}

	@Override
	public String toString() {
		try {
			return PortletPreferencesFactoryUtil.toXML(_portletPreferencesImpl);
		}
		catch (Exception e) {
			_log.error(e, e);

			return PortletConstants.DEFAULT_PREFERENCES;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TemplatePortletPreferences.class);

	private PortletPreferencesImpl _portletPreferencesImpl;

}