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

package com.liferay.portal.kernel.bi.reporting;

import com.liferay.portal.kernel.resource.ResourceRetriever;

import java.util.Date;

/**
 * @author Michael C. Han
 */
public interface ReportDesignRetriever extends ResourceRetriever {

	public Date getModifiedDate();

	public String getReportName();

}