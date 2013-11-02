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
public class MonitoringPortletManager implements MonitoringPortletManagerMBean {

	public void afterPropertiesSet() {
		if (MonitoringPortlet.isMonitoringPortletActionRequest() ||
			MonitoringPortlet.isMonitoringPortletEventRequest() ||
			MonitoringPortlet.isMonitoringPortletRenderRequest() ||
			MonitoringPortlet.isMonitoringPortletResourceRequest()) {

			setActive(true);
		}
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public boolean isMonitoringPortletActionRequest() {
		return MonitoringPortlet.isMonitoringPortletActionRequest();
	}

	@Override
	public boolean isMonitoringPortletEventRequest() {
		return MonitoringPortlet.isMonitoringPortletEventRequest();
	}

	@Override
	public boolean isMonitoringPortletRenderRequest() {
		return MonitoringPortlet.isMonitoringPortletRenderRequest();
	}

	@Override
	public boolean isMonitoringPortletResourceRequest() {
		return MonitoringPortlet.isMonitoringPortletResourceRequest();
	}

	@Override
	public void setActive(boolean active) {
		if (active == _active) {
			return;
		}

		PortletInstanceFactoryImpl portletInstanceFactoryImpl =
			new PortletInstanceFactoryImpl();

		if (active) {
			portletInstanceFactoryImpl.setInvokerPortletFactory(
				_monitoringPortletFactoryImpl);
		}
		else {
			portletInstanceFactoryImpl.setInvokerPortletFactory(
				_invokerPortletFactory);
		}

		PortletInstanceFactoryUtil portletInstanceFactoryUtil =
			new PortletInstanceFactoryUtil();

		portletInstanceFactoryUtil.setPortletInstanceFactory(
			portletInstanceFactoryImpl);

		_active = active;
	}

	public void setInvokerPortletFactory(
		InvokerPortletFactory invokerPortletFactory) {

		_invokerPortletFactory = invokerPortletFactory;
	}

	@Override
	public void setMonitoringPortletActionRequest(
		boolean monitoringPortletActionRequest) {

		MonitoringPortlet.setMonitoringPortletActionRequest(
			monitoringPortletActionRequest);
	}

	@Override
	public void setMonitoringPortletEventRequest(
		boolean monitoringPortletEventRequest) {

		MonitoringPortlet.setMonitoringPortletEventRequest(
			monitoringPortletEventRequest);
	}

	public void setMonitoringPortletFactoryImpl(
		InvokerPortletFactory monitoringPortletFactoryImpl) {

		_monitoringPortletFactoryImpl = monitoringPortletFactoryImpl;
	}

	@Override
	public void setMonitoringPortletRenderRequest(
		boolean monitoringPortletRenderRequest) {

		MonitoringPortlet.setMonitoringPortletRenderRequest(
			monitoringPortletRenderRequest);
	}

	@Override
	public void setMonitoringPortletResourceRequest(
		boolean monitoringPortletResourceRequest) {

		MonitoringPortlet.setMonitoringPortletResourceRequest(
			monitoringPortletResourceRequest);
	}

	private boolean _active;
	private InvokerPortletFactory _invokerPortletFactory;
	private InvokerPortletFactory _monitoringPortletFactoryImpl;

}