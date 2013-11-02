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

package com.liferay.portal.model;

import com.liferay.portal.kernel.xml.QName;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public interface PortletApp extends Serializable {

	public void addEventDefinition(EventDefinition eventDefinition);

	public void addPortlet(Portlet portlet);

	public void addPortletFilter(PortletFilter portletFilter);

	public void addPortletURLListener(PortletURLListener portletURLListener);

	public void addPublicRenderParameter(
		PublicRenderParameter publicRenderParameter);

	public void addPublicRenderParameter(String identifier, QName qName);

	public void addServletURLPatterns(Set<String> servletURLPatterns);

	public Map<String, String[]> getContainerRuntimeOptions();

	public String getContextPath();

	public Map<String, String> getCustomUserAttributes();

	public String getDefaultNamespace();

	public Set<EventDefinition> getEventDefinitions();

	public PortletFilter getPortletFilter(String filterName);

	public Set<PortletFilter> getPortletFilters();

	public List<Portlet> getPortlets();

	public PortletURLListener getPortletURLListener(String listenerClass);

	public Set<PortletURLListener> getPortletURLListeners();

	public PublicRenderParameter getPublicRenderParameter(String identifier);

	public String getServletContextName();

	public Set<String> getServletURLPatterns();

	public SpriteImage getSpriteImage(String fileName);

	public Set<String> getUserAttributes();

	public boolean isWARFile();

	public void setDefaultNamespace(String defaultNamespace);

	public void setSpriteImages(String spriteFileName, Properties properties);

	public void setWARFile(boolean warFile);

}