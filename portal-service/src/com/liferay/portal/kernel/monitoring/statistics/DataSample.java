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

import com.liferay.portal.kernel.monitoring.RequestStatus;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public interface DataSample {

	public long getCompanyId();

	public String getDescription();

	public long getDuration();

	public String getName();

	public String getNamespace();

	public RequestStatus getRequestStatus();

	public String getUser();

}