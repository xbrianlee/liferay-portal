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

import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Juan Fernández
 */
public interface TemplateHandler {

	/**
	 * Returns the class name of the template handler.
	 *
	 * @return the class name of the template handler
	 */
	public String getClassName();

	/**
	 * Returns a list of elements containing the information of the portlet
	 * display templates to be installed by default.
	 *
	 * @return a list of elements containing the information of the portlet
	 *         display templates to be installed by default. These templates
	 *         will be installed when registering the portlet
	 * @throws Exception if an exception occurred assembling the default
	 *         template elements
	 */
	public List<Element> getDefaultTemplateElements() throws Exception;

	/**
	 * Returns the name of the template handler.
	 *
	 * @param  locale the locale of the template handler name to get
	 * @return the name of the template handler
	 */
	public String getName(Locale locale);

	/**
	 * Returns the name of the resource the template is associated with.
	 * Permissions on the resource are checked when adding a new template.
	 *
	 * @return the name of the resource
	 */
	public String getResourceName();

	public String[] getRestrictedVariables(String language);

	/**
	 * Returns the path to the help template.
	 *
	 * @param  language the language of the template
	 * @return the path to the help template. This template will be shown as a
	 *         help message when the user creates a new template.
	 */
	public String getTemplatesHelpPath(String language);

	/**
	 * Returns the name of the property in portal.properties that defines the
	 * path to the help of template.
	 *
	 * @return the name of the property in portal.properties that defines the
	 *         path to the help template.
	 */
	public String getTemplatesHelpPropertyKey();

	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception;

}