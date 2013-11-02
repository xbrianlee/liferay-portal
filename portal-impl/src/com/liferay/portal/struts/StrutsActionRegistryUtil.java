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

import org.apache.struts.action.Action;

/**
 * @author Mika Koivisto
 * @author Raymond Augé
 */
public class StrutsActionRegistryUtil {

	public static Action getAction(String path) {
		return getStrutsActionRegistry().getAction(path);
	}

	public static Map<String, Action> getActions() {
		return getStrutsActionRegistry().getActions();
	}

	public static StrutsActionRegistry getStrutsActionRegistry() {
		return _strutsActionRegistry;
	}

	public static void register(String path, StrutsAction strutsAction) {
		getStrutsActionRegistry().register(path, strutsAction);
	}

	public static void register(
		String path, StrutsPortletAction strutsPortletAction) {

		getStrutsActionRegistry().register(path, strutsPortletAction);
	}

	public static void unregister(String path) {
		getStrutsActionRegistry().unregister(path);
	}

	public void setStrutsActionRegistry(
		StrutsActionRegistry strutsActionRegistry) {

		_strutsActionRegistry = strutsActionRegistry;
	}

	private static StrutsActionRegistry _strutsActionRegistry;

}