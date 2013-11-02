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

package com.liferay.portal.deploy.hot;

import com.liferay.portal.kernel.deploy.hot.BaseHotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.service.LayoutTemplateLocalServiceUtil;
import com.liferay.portal.util.WebKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 * @author Ivica Cardic
 */
public class LayoutTemplateHotDeployListener extends BaseHotDeployListener {

	@Override
	public void invokeDeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeDeploy(hotDeployEvent);
		}
		catch (Throwable t) {
			throwHotDeployException(
				hotDeployEvent,
				"Error registering layout templates for " +
					hotDeployEvent.getServletContextName(),
				t);
		}
	}

	@Override
	public void invokeUndeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeUndeploy(hotDeployEvent);
		}
		catch (Throwable t) {
			throwHotDeployException(
				hotDeployEvent,
				"Error unregistering layout templates for " +
					hotDeployEvent.getServletContextName(),
				t);
		}
	}

	protected void doInvokeDeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + servletContextName);
		}

		String[] xmls = new String[] {
			HttpUtil.URLtoString(
				servletContext.getResource(
					"/WEB-INF/liferay-layout-templates.xml"))
		};

		if (xmls[0] == null) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Registering layout templates for " + servletContextName);
		}

		List<LayoutTemplate> layoutTemplates =
			LayoutTemplateLocalServiceUtil.init(
				servletContextName, servletContext, xmls,
				hotDeployEvent.getPluginPackage());

		_layoutTemplates.put(servletContextName, layoutTemplates);

		servletContext.setAttribute(
			WebKeys.PLUGIN_LAYOUT_TEMPLATES, layoutTemplates);

		if (_log.isInfoEnabled()) {
			if (layoutTemplates.size() == 1) {
				_log.info(
					"1 layout template for " + servletContextName +
						" is available for use");
			}
			else {
				_log.info(
					layoutTemplates.size() + " layout templates for " +
						servletContextName + " are available for use");
			}
		}
	}

	protected void doInvokeUndeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking undeploy for " + servletContextName);
		}

		List<LayoutTemplate> layoutTemplates = _layoutTemplates.get(
			servletContextName);

		if (layoutTemplates == null) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Unregistering layout templates for " + servletContextName);
		}

		for (LayoutTemplate layoutTemplate : layoutTemplates) {
			try {
				LayoutTemplateLocalServiceUtil.uninstallLayoutTemplate(
					layoutTemplate.getLayoutTemplateId(),
					layoutTemplate.isStandard());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isInfoEnabled()) {
			if (layoutTemplates.size() == 1) {
				_log.info(
					"1 layout template for " + servletContextName +
						" was unregistered");
			}
			else {
				_log.info(
					layoutTemplates.size() + " layout templates for " +
						servletContextName + " were unregistered");
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LayoutTemplateHotDeployListener.class);

	private static Map<String, List<LayoutTemplate>> _layoutTemplates =
		new HashMap<String, List<LayoutTemplate>>();

}