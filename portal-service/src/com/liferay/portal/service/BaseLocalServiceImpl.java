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

package com.liferay.portal.service;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseLocalServiceImpl implements BaseLocalService {

	protected ClassLoader getClassLoader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	protected String getLayoutURL(
		Layout layout, ServiceContext serviceContext) {

		HttpServletRequest request = serviceContext.getRequest();

		if (request == null) {
			return StringPool.BLANK;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			return PortalUtil.getLayoutURL(layout, themeDisplay);
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	protected String getLayoutURL(
			long groupId, String portletId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String layoutURL = StringPool.BLANK;

		long plid = serviceContext.getPlid();

		long controlPanelPlid = PortalUtil.getControlPanelPlid(
			serviceContext.getCompanyId());

		if (plid == controlPanelPlid) {
			plid = PortalUtil.getPlidFromPortletId(groupId, portletId);

			if (plid != LayoutConstants.DEFAULT_PLID) {
				Layout layout = layoutPersistence.findByPrimaryKey(plid);

				layoutURL = getLayoutURL(layout, serviceContext);
			}
		}

		return layoutURL;
	}

	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;

}