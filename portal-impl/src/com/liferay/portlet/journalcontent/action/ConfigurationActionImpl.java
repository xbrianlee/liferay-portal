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

package com.liferay.portlet.journalcontent.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

import java.lang.reflect.Method;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Douglas Wong
 * @author Raymond Augé
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	public ConfigurationActionImpl() {
		try {
			Class<?> clazz = getClass();

			_doProcessActionMethod = clazz.getDeclaredMethod(
				"doProcessAction",
				new Class<?>[] {
					PortletConfig.class, ActionRequest.class,
					ActionResponse.class
				});
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		// This logic has to run in a transaction which we will invoke directly
		// since this is not a Spring bean

		ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, ConfigurationActionImpl.class, _doProcessActionMethod,
			new Object[] {portletConfig, actionRequest, actionResponse},
			new String[] {"transactionAdvice"});
	}

	/**
	 * This method is invoked in a transaction because we may result in a
	 * persistence call before and/or after the call to super.processAction()
	 * which itself results in a persistence call.
	 */
	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	protected void doProcessAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String[] extensions = actionRequest.getParameterValues("extensions");

		setPreference(actionRequest, "extensions", extensions);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences = actionRequest.getPreferences();

		String articleId = getArticleId(actionRequest);

		String originalArticleId = preferences.getValue("articleId", null);

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletResource);

		PortletLayoutListener portletLayoutListener =
			portlet.getPortletLayoutListenerInstance();

		if ((portletLayoutListener != null) &&
			Validator.isNotNull(originalArticleId) &&
			!originalArticleId.equals(articleId)) {

			// Results in a persistence call

			portletLayoutListener.onRemoveFromLayout(
				portletResource, layout.getPlid());
		}

		// Results in a persistence call

		super.processAction(portletConfig, actionRequest, actionResponse);

		if (SessionErrors.isEmpty(actionRequest) &&
			(portletLayoutListener != null)) {

			// Results in a persistence call

			portletLayoutListener.onAddToLayout(
				portletResource, layout.getPlid());
		}
	}

	protected String getArticleId(PortletRequest portletRequest) {
		String articleId = getParameter(portletRequest, "articleId");

		return StringUtil.toUpperCase(articleId);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ConfigurationActionImpl.class);

	private Method _doProcessActionMethod;

}