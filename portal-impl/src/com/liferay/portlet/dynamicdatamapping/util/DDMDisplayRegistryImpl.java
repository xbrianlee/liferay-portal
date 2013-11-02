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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.util.ListUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Eduardo Garcia
 */
public class DDMDisplayRegistryImpl implements DDMDisplayRegistry {

	@Override
	public DDMDisplay getDDMDisplay(String portletId) {
		return _ddmDisplays.get(portletId);
	}

	@Override
	public List<DDMDisplay> getDDMDisplays() {
		return ListUtil.fromMapValues(_ddmDisplays);
	}

	@Override
	public String[] getPortletIds() {
		Set<String> portletIds = _ddmDisplays.keySet();

		return portletIds.toArray(new String[portletIds.size()]);
	}

	@Override
	public void register(DDMDisplay ddmDisplay) {
		_ddmDisplays.put(ddmDisplay.getPortletId(), ddmDisplay);
	}

	@Override
	public void unregister(DDMDisplay ddmDisplay) {
		_ddmDisplays.remove(ddmDisplay.getPortletId());
	}

	private Map<String, DDMDisplay> _ddmDisplays =
		new HashMap<String, DDMDisplay>();

}