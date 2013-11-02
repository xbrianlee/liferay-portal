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

package com.liferay.portal.monitoring.statistics.portlet;

import com.liferay.portal.kernel.monitoring.MonitoringException;
import com.liferay.portal.kernel.monitoring.statistics.SummaryStatistics;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public interface PortletSummaryStatistics extends SummaryStatistics {

	public long getAverageTimeByPortlet(String portletId)
		throws MonitoringException;

	public long getAverageTimeByPortlet(String portletId, long companyId)
		throws MonitoringException;

	public long getAverageTimeByPortlet(String portletId, String webId)
		throws MonitoringException;

	public long getErrorCountByPortlet(String portletId)
		throws MonitoringException;

	public long getErrorCountByPortlet(String portletId, long companyId)
		throws MonitoringException;

	public long getErrorCountByPortlet(String portletId, String webId)
		throws MonitoringException;

	public long getMaxTimeByPortlet(String portletId)
		throws MonitoringException;

	public long getMaxTimeByPortlet(String portletId, long companyId)
		throws MonitoringException;

	public long getMaxTimeByPortlet(String portletId, String webId)
		throws MonitoringException;

	public long getMinTimeByPortlet(String portletId)
		throws MonitoringException;

	public long getMinTimeByPortlet(String portletId, long companyId)
		throws MonitoringException;

	public long getMinTimeByPortlet(String portletId, String webId)
		throws MonitoringException;

	public long getRequestCountByPortlet(String portletId)
		throws MonitoringException;

	public long getRequestCountByPortlet(String portletId, long companyId)
		throws MonitoringException;

	public long getRequestCountByPortlet(String portletId, String webId)
		throws MonitoringException;

	public long getSuccessCountByPortlet(String portletId)
		throws MonitoringException;

	public long getSuccessCountByPortlet(String portletId, long companyId)
		throws MonitoringException;

	public long getSuccessCountByPortlet(String portletId, String webId)
		throws MonitoringException;

	public long getTimeoutCountByPortlet(String portletId)
		throws MonitoringException;

	public long getTimeoutCountByPortlet(String portletId, long companyId)
		throws MonitoringException;

	public long getTimeoutCountByPortlet(String portletId, String webId)
		throws MonitoringException;

}