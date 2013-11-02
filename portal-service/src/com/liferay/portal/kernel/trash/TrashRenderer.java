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

package com.liferay.portal.kernel.trash;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Zsolt Berentey
 */
public interface TrashRenderer {

	public String getClassName();

	public long getClassPK();

	public String getIconPath(PortletRequest portletRequest);

	public String getNewName(String oldName, String token);

	public String getPortletId();

	public String getSummary(Locale locale);

	public String getTitle(Locale locale);

	public String getType();

	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception;

	public String renderActions(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception;

}