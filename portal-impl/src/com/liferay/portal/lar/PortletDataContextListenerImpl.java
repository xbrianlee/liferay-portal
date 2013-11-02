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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataContextListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Raymond Augé
 */
public class PortletDataContextListenerImpl
	implements PortletDataContextListener {

	public PortletDataContextListenerImpl(
		PortletDataContext portletDataContext) {
	}

	@Override
	public void onAddZipEntry(String path) {
		if (_log.isInfoEnabled()) {
			_log.info("Export " + path);
		}
	}

	@Override
	public void onGetZipEntry(String path) {
		if (_log.isInfoEnabled()) {
			_log.info("Import " + path);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletDataContextListenerImpl.class);

}