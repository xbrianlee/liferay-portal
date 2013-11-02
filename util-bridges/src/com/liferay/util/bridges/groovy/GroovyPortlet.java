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

package com.liferay.util.bridges.groovy;

import com.liferay.util.bridges.scripting.ScriptingPortlet;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;

/**
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Brian Wing Shun Chan
 */
public class GroovyPortlet extends ScriptingPortlet {

	@Override
	public void init() throws PortletException {
		super.init();

		language = _LANGUAGE;
	}

	@Override
	protected String getFileName(RenderRequest renderRequest) {
		return renderRequest.getParameter("groovyFile");
	}

	private static final String _LANGUAGE = "groovy";

}