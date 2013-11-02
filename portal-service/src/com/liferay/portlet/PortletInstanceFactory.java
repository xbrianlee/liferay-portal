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

package com.liferay.portlet;

import com.liferay.portal.model.Portlet;

import javax.portlet.PortletException;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public interface PortletInstanceFactory {

	public void clear(Portlet portlet);

	public void clear(Portlet portlet, boolean resetRemotePortletBag);

	public InvokerPortlet create(Portlet portlet, ServletContext servletContext)
		throws PortletException;

	public void delete(Portlet portlet);

	public void destroy(Portlet portlet);

}