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

import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.InitialThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tina Tian
 */
public class TemplateResourceThreadLocal {

	public static TemplateResource getTemplateResource(String templateType) {
		Map<String, TemplateResource> templateResources =
			_templateResources.get();

		return templateResources.get(templateType);
	}

	public static void setTemplateResource(
		String templateType, TemplateResource templateResource) {

		Map<String, TemplateResource> templateResources =
			_templateResources.get();

		templateResources.put(templateType, templateResource);
	}

	private static ThreadLocal<Map<String, TemplateResource>>
		_templateResources =
			new InitialThreadLocal<Map<String, TemplateResource>>(
				TemplateResourceThreadLocal.class.getName() +
					"._templateResources",
				new HashMap<String, TemplateResource>());

}