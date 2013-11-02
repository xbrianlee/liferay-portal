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

package com.liferay.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class DefaultControlPanelEntryFactory {

	public static ControlPanelEntry getInstance() {
		return _controlPanelEntry;
	}

	public static void setInstance(ControlPanelEntry controlPanelEntry) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(controlPanelEntry));
		}

		if (controlPanelEntry == null) {
			_controlPanelEntry = _originalControlPanelEntry;
		}
		else {
			_controlPanelEntry = controlPanelEntry;
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Instantiate " +
					PropsValues.CONTROL_PANEL_DEFAULT_ENTRY_CLASS);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalControlPanelEntry =
			(ControlPanelEntry)InstanceFactory.newInstance(
				classLoader, PropsValues.CONTROL_PANEL_DEFAULT_ENTRY_CLASS);

		_controlPanelEntry = _originalControlPanelEntry;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultControlPanelEntryFactory.class);

	private static volatile ControlPanelEntry _controlPanelEntry;
	private static ControlPanelEntry _originalControlPanelEntry;

}