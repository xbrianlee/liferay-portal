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

package com.liferay.portal.kernel.workflow.comparator;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Michael C. Han
 */
@MessagingProxy(mode = ProxyMode.SYNC)
public interface WorkflowComparatorFactory {

	public OrderByComparator getDefinitionNameComparator(boolean ascending);

	public OrderByComparator getInstanceEndDateComparator(boolean ascending);

	public OrderByComparator getInstanceStartDateComparator(boolean ascending);

	public OrderByComparator getInstanceStateComparator(boolean ascending);

	public OrderByComparator getLogCreateDateComparator(boolean ascending);

	public OrderByComparator getLogUserIdComparator(boolean ascending);

	public OrderByComparator getTaskCompletionDateComparator(boolean ascending);

	public OrderByComparator getTaskCreateDateComparator(boolean ascending);

	public OrderByComparator getTaskDueDateComparator(boolean ascending);

	public OrderByComparator getTaskNameComparator(boolean ascending);

	public OrderByComparator getTaskUserIdComparator(boolean ascending);

}