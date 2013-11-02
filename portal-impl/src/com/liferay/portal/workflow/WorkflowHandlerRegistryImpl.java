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

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistry;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
@DoPrivileged
public class WorkflowHandlerRegistryImpl implements WorkflowHandlerRegistry {

	@Override
	public List<WorkflowHandler> getScopeableWorkflowHandlers() {
		return ListUtil.fromMapValues(_scopeableWorkflowHandlerMap);
	}

	@Override
	public WorkflowHandler getWorkflowHandler(String className) {
		return _workflowHandlerMap.get(className);
	}

	@Override
	public List<WorkflowHandler> getWorkflowHandlers() {
		return ListUtil.fromMapValues(_workflowHandlerMap);
	}

	@Override
	public void register(WorkflowHandler workflowHandler) {
		_workflowHandlerMap.put(
			workflowHandler.getClassName(), workflowHandler);

		if (workflowHandler.isScopeable()) {
			_scopeableWorkflowHandlerMap.put(
				workflowHandler.getClassName(), workflowHandler);
		}
	}

	@Override
	public void unregister(WorkflowHandler workflowHandler) {
		_workflowHandlerMap.remove(workflowHandler.getClassName());

		if (workflowHandler.isScopeable()) {
			_scopeableWorkflowHandlerMap.remove(workflowHandler.getClassName());
		}
	}

	private Map<String, WorkflowHandler> _scopeableWorkflowHandlerMap =
		//new ConcurrentSkipListMap<String, WorkflowHandler>();
		new TreeMap<String, WorkflowHandler>();
	private Map<String, WorkflowHandler> _workflowHandlerMap =
		new TreeMap<String, WorkflowHandler>();

}