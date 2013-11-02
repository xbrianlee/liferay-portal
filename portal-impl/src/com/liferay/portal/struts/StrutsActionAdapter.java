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

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.util.ClassLoaderUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mika Koivisto
 */
public class StrutsActionAdapter extends BaseStrutsAction {

	public StrutsActionAdapter(
		Action action, ActionMapping actionMapping, ActionForm actionForm) {

		_action = action;
		_actionMapping = actionMapping;
		_actionForm = actionForm;
	}

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		currentThread.setContextClassLoader(
			ClassLoaderUtil.getPortalClassLoader());

		try {
			ActionForward actionForward = _action.execute(
				_actionMapping, _actionForm, request, response);

			if (actionForward == null) {
				return null;
			}

			return actionForward.getPath();
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private Action _action;
	private ActionForm _actionForm;
	private ActionMapping _actionMapping;

}