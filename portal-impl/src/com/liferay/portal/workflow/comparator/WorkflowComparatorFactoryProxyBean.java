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

package com.liferay.portal.workflow.comparator;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactory;

/**
 * @author Shuyang Zhou
 */
public class WorkflowComparatorFactoryProxyBean
	extends BaseProxyBean implements WorkflowComparatorFactory {

	@Override
	public OrderByComparator getDefinitionNameComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getInstanceEndDateComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getInstanceStartDateComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getInstanceStateComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getLogCreateDateComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getLogUserIdComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getTaskCompletionDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getTaskCreateDateComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getTaskDueDateComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getTaskNameComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator getTaskUserIdComparator(boolean ascending) {
		throw new UnsupportedOperationException();
	}

}