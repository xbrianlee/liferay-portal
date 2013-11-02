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

package com.liferay.portal.kernel.workflow.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseDestinationEventListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;

/**
 * @author Michael C. Han
 */
public class DefaultWorkflowDestinationEventListener
	extends BaseDestinationEventListener {

	@Override
	public void messageListenerRegistered(
		String destinationName, MessageListener messageListener) {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Unregistering default workflow engine " + _workflowEngineName);
		}

		if (!isProceed(destinationName, messageListener)) {
			return;
		}

		MessageBusUtil.unregisterMessageListener(
			DestinationNames.WORKFLOW_DEFINITION,
			_workflowDefinitionManagerListener);

		MessageBusUtil.unregisterMessageListener(
			DestinationNames.WORKFLOW_ENGINE, _workflowEngineManagerListener);

		MessageBusUtil.unregisterMessageListener(
			DestinationNames.WORKFLOW_INSTANCE,
			_workflowInstanceManagerListener);

		MessageBusUtil.unregisterMessageListener(
			DestinationNames.WORKFLOW_LOG, _workflowLogManagerListener);

		MessageBusUtil.unregisterMessageListener(
			DestinationNames.WORKFLOW_TASK, _workflowTaskManagerListener);
	}

	@Override
	public void messageListenerUnregistered(
		String destinationName, MessageListener messageListener) {

		if (!isProceed(destinationName, messageListener)) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Registering default workflow engine " + _workflowEngineName);
		}

		MessageBusUtil.registerMessageListener(
			DestinationNames.WORKFLOW_DEFINITION,
			_workflowDefinitionManagerListener);

		MessageBusUtil.registerMessageListener(
			DestinationNames.WORKFLOW_ENGINE, _workflowEngineManagerListener);

		MessageBusUtil.registerMessageListener(
			DestinationNames.WORKFLOW_INSTANCE,
			_workflowInstanceManagerListener);

		MessageBusUtil.registerMessageListener(
			DestinationNames.WORKFLOW_LOG, _workflowLogManagerListener);

		MessageBusUtil.registerMessageListener(
			DestinationNames.WORKFLOW_TASK, _workflowTaskManagerListener);
	}

	public void setWorkflowDefinitionManagerListener(
		MessageListener workflowDefinitionManagerListener) {

		_workflowDefinitionManagerListener = workflowDefinitionManagerListener;
	}

	public void setWorkflowEngineManagerListener(
		MessageListener workflowEngineManagerListener) {

		_workflowEngineManagerListener = workflowEngineManagerListener;
	}

	public void setWorkflowEngineName(String workflowEngineName) {
		_workflowEngineName = workflowEngineName;
	}

	public void setWorkflowInstanceManagerListener(
		MessageListener workflowInstanceManagerListener) {

		_workflowInstanceManagerListener = workflowInstanceManagerListener;
	}

	public void setWorkflowLogManagerListener(
		MessageListener workflowLogManagerListener) {

		_workflowLogManagerListener = workflowLogManagerListener;
	}

	public void setWorkflowTaskManagerListener(
		MessageListener workflowTaskManagerListener) {

		_workflowTaskManagerListener = workflowTaskManagerListener;
	}

	protected boolean isProceed(
		String destinationName, MessageListener messageListener) {

		if (messageListener.equals(_workflowEngineManagerListener)) {
			return false;
		}
		else {
			return true;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultWorkflowDestinationEventListener.class);

	private MessageListener _workflowDefinitionManagerListener;
	private MessageListener _workflowEngineManagerListener;
	private String _workflowEngineName;
	private MessageListener _workflowInstanceManagerListener;
	private MessageListener _workflowLogManagerListener;
	private MessageListener _workflowTaskManagerListener;

}