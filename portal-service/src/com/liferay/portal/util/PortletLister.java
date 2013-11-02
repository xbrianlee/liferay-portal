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

package com.liferay.portal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.TreeView;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.ServletContext;

/**
 * @author Jorge Ferrer
 * @author Dennis Ju
 * @author Brian Wing Shun Chan
 */
public interface PortletLister {

	public TreeView getTreeView() throws PortalException, SystemException;

	public void setHierarchicalTree(boolean hierarchicalTree);

	public void setIncludeInstanceablePortlets(
		boolean includeInstanceablePortlets);

	public void setIteratePortlets(boolean iteratePortlets);

	public void setLayoutTypePortlet(LayoutTypePortlet layoutTypePortlet);

	public void setRootNodeName(String rootNodeName);

	public void setServletContext(ServletContext servletContext);

	public void setThemeDisplay(ThemeDisplay themeDisplay);

	public void setUser(User user);

}