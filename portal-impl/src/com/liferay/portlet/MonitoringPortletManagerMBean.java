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

/**
 * @author Michael C. Han
 */
public interface MonitoringPortletManagerMBean {

	public boolean isActive();

	public boolean isMonitoringPortletActionRequest();

	public boolean isMonitoringPortletEventRequest();

	public boolean isMonitoringPortletRenderRequest();

	public boolean isMonitoringPortletResourceRequest();

	public void setActive(boolean active);

	public void setMonitoringPortletActionRequest(
		boolean monitoringPortletActionRequest);

	public void setMonitoringPortletEventRequest(
		boolean monitoringPortletEventRequest);

	public void setMonitoringPortletRenderRequest(
		boolean monitoringPortletRenderRequest);

	public void setMonitoringPortletResourceRequest(
		boolean monitoringPortletResourceRequest);

}