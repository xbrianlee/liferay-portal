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

package com.liferay.portal.servlet.taglib.ui;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

import java.util.List;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author     Brian Chan
 * @author     Jorge Ferrer
 * @deprecated As of 6.2.0, replaced by {@link
 *             com.liferay.taglib.ui.InputPermissionsParamsTag}
 */
public class InputPermissionsParamsTagUtil {

	public static void doEndTag(String modelName, PageContext pageContext)
		throws JspException {

		try {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			RenderResponse renderResponse =
				(RenderResponse)request.getAttribute(
					JavaConstants.JAVAX_PORTLET_RESPONSE);

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Layout layout = themeDisplay.getLayout();

			Group layoutGroup = layout.getGroup();

			Group group = themeDisplay.getScopeGroup();

			List<String> supportedActions =
				ResourceActionsUtil.getModelResourceActions(modelName);
			List<String> groupDefaultActions =
				ResourceActionsUtil.getModelResourceGroupDefaultActions(
					modelName);
			List<String> guestDefaultActions =
				ResourceActionsUtil.getModelResourceGuestDefaultActions(
					modelName);
			List<String> guestUnsupportedActions =
				ResourceActionsUtil.getModelResourceGuestUnsupportedActions(
					modelName);

			StringBundler sb = new StringBundler();

			for (int i = 0; i < supportedActions.size(); i++) {
				String action = supportedActions.get(i);

				boolean groupChecked = groupDefaultActions.contains(action);

				boolean guestChecked = false;

				if (layoutGroup.isControlPanel()) {
					if (!group.hasPrivateLayouts() &&
						guestDefaultActions.contains(action)) {

						guestChecked = true;
					}
				}
				else if (layout.isPublicLayout() &&
						 guestDefaultActions.contains(action)) {

					guestChecked = true;
				}

				boolean guestDisabled = guestUnsupportedActions.contains(
					action);

				if (guestDisabled) {
					guestChecked = false;
				}

				if (group.isOrganization() || group.isRegularSite()) {
					if (groupChecked) {
						sb.append(StringPool.AMPERSAND);
						sb.append(renderResponse.getNamespace());
						sb.append("groupPermissions=");
						sb.append(HttpUtil.encodeURL(action));
					}
				}

				if (guestChecked) {
					sb.append(StringPool.AMPERSAND);
					sb.append(renderResponse.getNamespace());
					sb.append("guestPermissions=");
					sb.append(HttpUtil.encodeURL(action));
				}
			}

			String inputPermissionsViewRole = getDefaultViewRole(
				modelName, themeDisplay);

			sb.append(StringPool.AMPERSAND);
			sb.append(renderResponse.getNamespace());
			sb.append("inputPermissionsViewRole=");
			sb.append(HttpUtil.encodeURL(inputPermissionsViewRole));

			pageContext.getOut().print(sb.toString());
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public static String getDefaultViewRole(
			String modelName, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Layout layout = themeDisplay.getLayout();

		Group layoutGroup = layout.getGroup();

		List<String> guestDefaultActions =
			ResourceActionsUtil.getModelResourceGuestDefaultActions(modelName);

		if (layoutGroup.isControlPanel()) {
			Group group = themeDisplay.getScopeGroup();

			if (!group.hasPrivateLayouts() &&
				guestDefaultActions.contains(ActionKeys.VIEW)) {

				return RoleConstants.GUEST;
			}
		}
		else if (layout.isPublicLayout() &&
				 guestDefaultActions.contains(ActionKeys.VIEW)) {

			return RoleConstants.GUEST;
		}

		List<String> groupDefaultActions =
			ResourceActionsUtil.getModelResourceGroupDefaultActions(modelName);

		if (groupDefaultActions.contains(ActionKeys.VIEW)) {
			Group siteGroup = GroupLocalServiceUtil.getGroup(
				themeDisplay.getSiteGroupId());

			Role defaultGroupRole = RoleLocalServiceUtil.getDefaultGroupRole(
				siteGroup.getGroupId());

			return defaultGroupRole.getName();
		}

		return RoleConstants.OWNER;
	}

}