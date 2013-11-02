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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.staging.LayoutStagingUtil;
import com.liferay.portal.kernel.staging.MergeLayoutPrototypesThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.PortletPreferencesIds;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.LayoutRevisionUtil;
import com.liferay.portal.staging.ProxiedLayoutsThreadLocal;
import com.liferay.portal.staging.StagingAdvicesThreadLocal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Raymond Augé
 */
public class PortletPreferencesLocalServiceStagingAdvice
	implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (!StagingAdvicesThreadLocal.isEnabled()) {
			return methodInvocation.proceed();
		}

		try {
			Object[] arguments = methodInvocation.getArguments();

			if (arguments == null) {
				return methodInvocation.proceed();
			}

			Method method = methodInvocation.getMethod();

			String methodName = method.getName();

			if (methodName.equals("getPortletPreferences") &&
				((arguments.length == 3) || (arguments.length == 4))) {

				return getPortletPreferences(methodInvocation);
			}
			else if (methodName.equals("getPortletPreferencesCount") &&
					 ((arguments.length == 3) || (arguments.length == 5))) {

				return getPortletPreferencesCount(methodInvocation);
			}
			else if (methodName.equals("getPreferences")) {
				return getPreferences(methodInvocation);
			}
			else if (methodName.equals("getStrictPreferences")) {
				return getPreferences(methodInvocation);
			}
			else if (methodName.equals("updatePreferences")) {
				return updatePreferences(methodInvocation);
			}

			return methodInvocation.proceed();
		}
		catch (InvocationTargetException ite) {
			throw ite.getCause();
		}
		catch (Throwable throwable) {
			throw throwable;
		}
	}

	protected Object getPortletPreferences(MethodInvocation methodInvocation)
		throws Throwable {

		Method method = methodInvocation.getMethod();
		Object[] arguments = methodInvocation.getArguments();

		long plid = 0;

		if (((arguments.length == 3) || (arguments.length == 4)) &&
			(arguments[2] instanceof Long)) {

			plid = (Long)arguments[2];
		}

		if (plid <= 0) {
			return methodInvocation.proceed();
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		if (!LayoutStagingUtil.isBranchingLayout(layout)) {
			return methodInvocation.proceed();
		}

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		arguments[2] = layoutRevision.getLayoutRevisionId();

		return method.invoke(methodInvocation.getThis(), arguments);
	}

	protected Object getPortletPreferencesCount(
			MethodInvocation methodInvocation)
		throws Throwable {

		Method method = methodInvocation.getMethod();
		Object[] arguments = methodInvocation.getArguments();

		long plid = LayoutConstants.DEFAULT_PLID;

		if (arguments.length == 3) {
			plid = (Long)arguments[1];
		}
		else {
			plid = (Long)arguments[2];
		}

		if (plid <= 0) {
			return methodInvocation.proceed();
		}

		Layout layout = LayoutLocalServiceUtil.fetchLayout(plid);

		if (layout == null) {
			return methodInvocation.proceed();
		}

		if (!LayoutStagingUtil.isBranchingLayout(layout)) {
			return methodInvocation.proceed();
		}

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (arguments.length == 3) {
			arguments[1] = layoutRevision.getLayoutRevisionId();
		}
		else {
			arguments[2] = layoutRevision.getLayoutRevisionId();
		}

		return method.invoke(methodInvocation.getThis(), arguments);
	}

	protected Object getPreferences(MethodInvocation methodInvocation)
		throws Throwable {

		Method method = methodInvocation.getMethod();
		Object[] arguments = methodInvocation.getArguments();

		long plid = 0;

		if (arguments.length == 1) {
			PortletPreferencesIds portletPreferencesIds =
				(PortletPreferencesIds)arguments[0];

			plid = portletPreferencesIds.getPlid();
		}
		else {
			plid = (Long)arguments[3];
		}

		if (plid <= 0) {
			return methodInvocation.proceed();
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		if (!LayoutStagingUtil.isBranchingLayout(layout)) {
			return methodInvocation.proceed();
		}

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision == null) {
			return methodInvocation.proceed();
		}

		plid = layoutRevision.getLayoutRevisionId();

		if (arguments.length == 1) {
			PortletPreferencesIds portletPreferencesIds =
				(PortletPreferencesIds)arguments[0];

			portletPreferencesIds.setPlid(plid);
		}
		else {
			arguments[3] = plid;
		}

		return method.invoke(methodInvocation.getThis(), arguments);
	}

	protected Object updatePreferences(MethodInvocation methodInvocation)
		throws Throwable {

		Method method = methodInvocation.getMethod();
		Object[] arguments = methodInvocation.getArguments();

		long plid = (Long)arguments[2];

		if (plid <= 0) {
			return methodInvocation.proceed();
		}

		LayoutRevision layoutRevision = LayoutRevisionUtil.fetchByPrimaryKey(
			plid);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return methodInvocation.proceed();
		}

		boolean exporting = ParamUtil.getBoolean(serviceContext, "exporting");

		if ((layoutRevision == null) || exporting) {
			return methodInvocation.proceed();
		}

		if (!MergeLayoutPrototypesThreadLocal.isInProgress()) {
			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		layoutRevision = LayoutRevisionLocalServiceUtil.updateLayoutRevision(
			serviceContext.getUserId(), layoutRevision.getLayoutRevisionId(),
			layoutRevision.getLayoutBranchId(), layoutRevision.getName(),
			layoutRevision.getTitle(), layoutRevision.getDescription(),
			layoutRevision.getKeywords(), layoutRevision.getRobots(),
			layoutRevision.getTypeSettings(), layoutRevision.getIconImage(),
			layoutRevision.getIconImageId(), layoutRevision.getThemeId(),
			layoutRevision.getColorSchemeId(), layoutRevision.getWapThemeId(),
			layoutRevision.getWapColorSchemeId(), layoutRevision.getCss(),
			serviceContext);

		arguments[2] = layoutRevision.getLayoutRevisionId();

		ProxiedLayoutsThreadLocal.clearProxiedLayouts();

		return method.invoke(methodInvocation.getThis(), arguments);
	}

}