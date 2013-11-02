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

package com.liferay.portal.kernel.messaging.jmx;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public interface DestinationStatisticsManagerMBean {

	public int getActiveThreadCount();

	public int getCurrentThreadCount();

	public int getLargestThreadCount();

	public String getLastRefresh();

	public int getMaxThreadPoolSize();

	public int getMinThreadPoolSize();

	public long getPendingMessageCount();

	public long getSentMessageCount();

	public boolean isAutoRefresh();

	public void refresh();

	public void setAutoRefresh(boolean autoRefresh);

}