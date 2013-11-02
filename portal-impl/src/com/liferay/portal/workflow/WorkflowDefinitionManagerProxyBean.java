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

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;

import java.util.List;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
public class WorkflowDefinitionManagerProxyBean
	extends BaseProxyBean implements WorkflowDefinitionManager {

	@Override
	public WorkflowDefinition deployWorkflowDefinition(
		long companyId, long userId, String title, byte[] bytes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getActiveWorkflowDefinitionCount(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getActiveWorkflowDefinitionCount(long companyId, String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
		long companyId, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
		long companyId, String name, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition getLatestKaleoDefinition(
		long companyId, String name) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition getWorkflowDefinition(
		long companyId, String name, int version) {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getWorkflowDefinitionCount(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getWorkflowDefinitionCount(long companyId, String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getWorkflowDefinitions(
		long companyId, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getWorkflowDefinitions(
		long companyId, String name, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void undeployWorkflowDefinition(
		long companyId, long userId, String name, int version) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition updateActive(
		long companyId, long userId, String name, int version, boolean active) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition updateTitle(
		long companyId, long userId, String name, int version, String title) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void validateWorkflowDefinition(byte[] bytes) {
		throw new UnsupportedOperationException();
	}

}