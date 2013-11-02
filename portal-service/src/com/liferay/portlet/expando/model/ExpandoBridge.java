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

package com.liferay.portlet.expando.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public interface ExpandoBridge {

	public void addAttribute(String name) throws PortalException;

	public void addAttribute(String name, boolean secure)
		throws PortalException;

	public void addAttribute(String name, int type) throws PortalException;

	public void addAttribute(String name, int type, boolean secure)
		throws PortalException;

	public void addAttribute(String name, int type, Serializable defaultValue)
		throws PortalException;

	public void addAttribute(
			String name, int type, Serializable defaultValue, boolean secure)
		throws PortalException;

	public Serializable getAttribute(String name);

	public Serializable getAttribute(String name, boolean secure);

	public Serializable getAttributeDefault(String name);

	public Enumeration<String> getAttributeNames();

	public UnicodeProperties getAttributeProperties(String name);

	public Map<String, Serializable> getAttributes();

	public Map<String, Serializable> getAttributes(boolean secure);

	public Map<String, Serializable> getAttributes(Collection<String> names);

	public Map<String, Serializable> getAttributes(
		Collection<String> names, boolean secure);

	public int getAttributeType(String name);

	public String getClassName();

	public long getClassPK();

	public long getCompanyId();

	public boolean hasAttribute(String name);

	public boolean isIndexEnabled();

	public void setAttribute(String name, Serializable value);

	public void setAttribute(String name, Serializable value, boolean secure);

	public void setAttributeDefault(String name, Serializable defaultValue);

	public void setAttributeProperties(
		String name, UnicodeProperties properties);

	public void setAttributeProperties(
		String name, UnicodeProperties properties, boolean secure);

	public void setAttributes(Map<String, Serializable> attributes);

	public void setAttributes(
		Map<String, Serializable> attributes, boolean secure);

	public void setAttributes(ServiceContext serviceContext);

	public void setAttributes(ServiceContext serviceContext, boolean secure);

	public void setClassName(String className);

	public void setClassPK(long classPK);

	public void setCompanyId(long companyId);

	public void setIndexEnabled(boolean indexEnabled);

}