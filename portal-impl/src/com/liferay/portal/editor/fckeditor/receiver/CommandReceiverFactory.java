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

package com.liferay.portal.editor.fckeditor.receiver;

import com.liferay.portal.kernel.util.InstancePool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Michael C. Han
 */
public class CommandReceiverFactory {

	public static CommandReceiver getCommandReceiver(String type) {
		CommandReceiver commandReceiver = _commandReceivers.get(type);

		if (commandReceiver == null) {
			commandReceiver = (CommandReceiver)InstancePool.get(
				"com.liferay.portal.editor.fckeditor.receiver.impl." +
					type + "CommandReceiver");

			_commandReceivers.put(type, commandReceiver);
		}

		return commandReceiver;
	}

	public void setCommandReceiver(
		String type, CommandReceiver commandReceiver) {

		_commandReceivers.put(type, commandReceiver);
	}

	public void setCommandReceivers(
		Map<String, CommandReceiver> commandReceivers) {

		_commandReceivers.putAll(commandReceivers);
	}

	private static Map<String, CommandReceiver> _commandReceivers =
		new HashMap<String, CommandReceiver>();

}