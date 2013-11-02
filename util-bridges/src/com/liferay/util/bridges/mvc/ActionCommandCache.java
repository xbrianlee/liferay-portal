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

package com.liferay.util.bridges.mvc;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 */
public class ActionCommandCache {

	public static final String ACTION_PACKAGE_NAME = "action.package.prefix";

	public static final ActionCommand EMPTY = new ActionCommand() {

		@Override
		public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse) {

			return false;
		}

	};

	public ActionCommandCache(String packagePrefix) {
		if (!packagePrefix.endsWith(StringPool.PERIOD)) {
			packagePrefix = packagePrefix + StringPool.PERIOD;
		}

		_packagePrefix = packagePrefix;
	}

	public ActionCommand getActionCommand(String actionCommandName) {
		String className = null;

		try {
			ActionCommand actionCommand = _actionCommandCache.get(
				actionCommandName);

			if (actionCommand != null) {
				return actionCommand;
			}

			StringBundler sb = new StringBundler(4);

			sb.append(_packagePrefix);
			sb.append(Character.toUpperCase(actionCommandName.charAt(0)));
			sb.append(actionCommandName.substring(1));
			sb.append(_ACTION_COMMAND_POSTFIX);

			className = sb.toString();

			actionCommand = (ActionCommand)InstanceFactory.newInstance(
				className);

			_actionCommandCache.put(actionCommandName, actionCommand);

			return actionCommand;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to instantiate ActionCommand " + className);
			}

			_actionCommandCache.put(actionCommandName, EMPTY);

			return EMPTY;
		}
	}

	public List<ActionCommand> getActionCommandChain(
		String actionCommandChain) {

		List<ActionCommand> actionCommands = _actionCommandChainCache.get(
			actionCommandChain);

		if (actionCommands != null) {
			return actionCommands;
		}

		actionCommands = new ArrayList<ActionCommand>();

		String[] actionCommandNames = StringUtil.split(actionCommandChain);

		for (String actionCommandName : actionCommandNames) {
			ActionCommand actionCommand = getActionCommand(actionCommandName);

			if (actionCommand != EMPTY) {
				actionCommands.add(actionCommand);
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to find ActionCommand " + actionCommandChain);
				}
			}
		}

		_actionCommandChainCache.put(actionCommandChain, actionCommands);

		return actionCommands;
	}

	public boolean isEmpty() {
		return _actionCommandCache.isEmpty();
	}

	private static final String _ACTION_COMMAND_POSTFIX = "ActionCommand";

	private static Log _log = LogFactoryUtil.getLog(ActionCommandCache.class);

	private Map<String, ActionCommand> _actionCommandCache =
		new ConcurrentHashMap<String, ActionCommand>();
	private Map<String, List<ActionCommand>> _actionCommandChainCache =
		new ConcurrentHashMap<String, List<ActionCommand>>();
	private String _packagePrefix;

}