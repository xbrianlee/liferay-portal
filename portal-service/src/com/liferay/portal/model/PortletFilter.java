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

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public interface PortletFilter extends Serializable {

	public String getFilterClass();

	public String getFilterName();

	public Map<String, String> getInitParams();

	public Set<String> getLifecycles();

	public PortletApp getPortletApp();

	public void setFilterClass(String filterClass);

	public void setFilterName(String filterName);

	public void setInitParams(Map<String, String> initParams);

	public void setLifecycles(Set<String> lifecycles);

	public void setPortletApp(PortletApp portletApp);

}