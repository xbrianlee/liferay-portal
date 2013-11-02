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

/**
 * @author Tina Tian
 */
public interface TemplateManager {

	public void destroy();

	public void destroy(ClassLoader classLoader);

	public String getName();

	public Template getTemplate(
		TemplateResource templateResource, boolean restricted);

	public Template getTemplate(
		TemplateResource templateResource,
		TemplateResource errorTemplateResource, boolean restricted);

	public void init() throws TemplateException;

}