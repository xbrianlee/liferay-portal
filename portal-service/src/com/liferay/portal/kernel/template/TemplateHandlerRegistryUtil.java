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

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Juan Fernández
 */
public class TemplateHandlerRegistryUtil {

	public static long[] getClassNameIds() {
		return getTemplateRegistry().getClassNameIds();
	}

	public static TemplateHandler getTemplateHandler(long classNameId) {
		return getTemplateRegistry().getTemplateHandler(classNameId);
	}

	public static TemplateHandler getTemplateHandler(String className) {
		return getTemplateRegistry().getTemplateHandler(className);
	}

	public static List<TemplateHandler> getTemplateHandlers() {
		return getTemplateRegistry().getTemplateHandlers();
	}

	public static TemplateHandlerRegistry getTemplateRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			TemplateHandlerRegistryUtil.class);

		return _templateHandlerRegistry;
	}

	public static void register(TemplateHandler templateHandler) {
		getTemplateRegistry().register(templateHandler);
	}

	public static void unregister(TemplateHandler templateHandler) {
		getTemplateRegistry().unregister(templateHandler);
	}

	public void setTemplateHandlerRegistry(
		TemplateHandlerRegistry templateHandlerRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_templateHandlerRegistry = templateHandlerRegistry;
	}

	private static TemplateHandlerRegistry _templateHandlerRegistry;

}