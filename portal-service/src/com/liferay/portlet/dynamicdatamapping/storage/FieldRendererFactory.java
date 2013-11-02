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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;

/**
 * @author Bruno Basto
 */
public class FieldRendererFactory {

	public static FieldRenderer getFieldRenderer(String dataType) {
		FieldRenderer fieldRenderer = _fieldRenderers.get(dataType);

		if (fieldRenderer == null) {
			fieldRenderer = _fieldRenderers.get(FieldConstants.STRING);
		}

		return fieldRenderer;
	}

	public void setFieldRenderers(Map<String, FieldRenderer> fieldRenderers) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_fieldRenderers = fieldRenderers;
	}

	private static Map<String, FieldRenderer> _fieldRenderers;

}