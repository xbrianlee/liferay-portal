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

package com.liferay.portal.kernel.monitoring.statistics;

import com.liferay.portal.kernel.monitoring.MonitoringException;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public interface SummaryStatistics {

	public long getAverageTime() throws MonitoringException;

	public long getAverageTimeByCompany(long companyId)
		throws MonitoringException;

	public long getAverageTimeByCompany(String webId)
		throws MonitoringException;

	public long getErrorCount() throws MonitoringException;

	public long getErrorCountByCompany(long companyId)
		throws MonitoringException;

	public long getErrorCountByCompany(String webId) throws MonitoringException;

	public long getMaxTime() throws MonitoringException;

	public long getMaxTimeByCompany(long companyId) throws MonitoringException;

	public long getMaxTimeByCompany(String webId) throws MonitoringException;

	public long getMinTime() throws MonitoringException;

	public long getMinTimeByCompany(long companyId) throws MonitoringException;

	public long getMinTimeByCompany(String webId) throws MonitoringException;

	public long getRequestCount() throws MonitoringException;

	public long getRequestCountByCompany(long companyId)
		throws MonitoringException;

	public long getRequestCountByCompany(String webId)
		throws MonitoringException;

	public long getSuccessCount() throws MonitoringException;

	public long getSuccessCountByCompany(long companyId)
		throws MonitoringException;

	public long getSuccessCountByCompany(String webId)
		throws MonitoringException;

	public long getTimeoutCount() throws MonitoringException;

	public long getTimeoutCountByCompany(long companyId)
		throws MonitoringException;

	public long getTimeoutCountByCompany(String webId)
		throws MonitoringException;

}