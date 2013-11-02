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
 * @author Raymond Augé
 */
public interface StrutsActionRegistry {

	public Action getAction(String path);

	public Map<String, Action> getActions();

	public void register(String path, StrutsAction strutsAction);

	public void register(String path, StrutsPortletAction strutsPortletAction);

	public void unregister(String path);

}