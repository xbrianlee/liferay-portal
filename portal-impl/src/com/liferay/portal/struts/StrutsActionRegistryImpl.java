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

package com.liferay.portal.struts;

import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.struts.action.Action;

/**
 * @author Mika Koivisto
 * @author Raymond Augé
 */
public class StrutsActionRegistryImpl implements StrutsActionRegistry {

	@Override
	public Action getAction(String path) {
		Action action = _actions.get(path);

		if (action != null) {
			return action;
		}

		for (Map.Entry<String, Action> entry : _actions.entrySet()) {
			if (path.startsWith(entry.getKey())) {
				return entry.getValue();
			}
		}

		return null;
	}

	@Override
	public Map<String, Action> getActions() {
		return _actions;
	}

	@Override
	public void register(String path, StrutsAction strutsAction) {
		Action action = new ActionAdapter(strutsAction);

		_actions.put(path, action);
	}

	@Override
	public void register(String path, StrutsPortletAction strutsPortletAction) {
		Action action = new PortletActionAdapter(strutsPortletAction);

		_actions.put(path, action);
	}

	@Override
	public void unregister(String path) {
		_actions.remove(path);
	}

	private static Map<String, Action> _actions =
		new ConcurrentHashMap<String, Action>();

}