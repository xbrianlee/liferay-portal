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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Collections;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class HotDeployMessageListener extends BaseMessageListener {

	public HotDeployMessageListener() {
		this((String[])null);
	}

	public HotDeployMessageListener(String... servletContextNames) {
		if (servletContextNames == null) {
			_servletContextNames = Collections.emptySet();
		}
		else {
			_servletContextNames = SetUtil.fromArray(servletContextNames);
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String servletContextName = GetterUtil.getString(
			message.getString("servletContextName"));

		if (!_servletContextNames.isEmpty() &&
			!_servletContextNames.contains(servletContextName)) {

			return;
		}

		String command = GetterUtil.getString(message.getString("command"));

		if (command.equals("deploy")) {
			onDeploy(message);
		}
		else if (command.equals("undeploy")) {
			onUndeploy(message);
		}
	}

	protected void onDeploy(Message message) throws Exception {
	}

	protected void onUndeploy(Message message) throws Exception {
	}

	private Set<String> _servletContextNames;

}