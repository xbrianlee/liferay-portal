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

package com.liferay.portal.monitoring.jmx;

import com.liferay.portal.kernel.monitoring.Level;
import com.liferay.portal.kernel.monitoring.MonitoringProcessor;

import java.util.Set;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class MonitoringProcessorManager
	implements MonitoringProcessorManagerMBean {

	@Override
	public String getLevel(String namespace) {
		Level level = _monitoringProcessor.getLevel(namespace);

		if (level == null) {
			level = Level.OFF;
		}

		return level.toString();
	}

	@Override
	public String[] getNamespaces() {
		Set<String> namespaces = _monitoringProcessor.getNamespaces();

		return namespaces.toArray(new String[namespaces.size()]);
	}

	@Override
	public void setLevel(String namespace, String levelName) {
		Level level = Level.valueOf(levelName);

		_monitoringProcessor.setLevel(namespace, level);
	}

	public void setMonitoringProcessor(
		MonitoringProcessor monitoringProcessor) {

		_monitoringProcessor = monitoringProcessor;
	}

	private MonitoringProcessor _monitoringProcessor;

}