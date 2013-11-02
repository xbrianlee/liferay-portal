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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Bruno Farache
 * @author Macerllus Tavares
 * @author Juan Fernández
 * @author Julio Camarero
 */
public interface WorkflowHandler {

	public AssetRenderer getAssetRenderer(long classPK)
		throws PortalException, SystemException;

	public AssetRendererFactory getAssetRendererFactory();

	public String getClassName();

	public String getIconPath(LiferayPortletRequest liferayPortletRequest);

	public String getSummary(long classPK, Locale locale);

	public String getTitle(long classPK, Locale locale);

	public String getType(Locale locale);

	public PortletURL getURLEdit(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse);

	public String getURLViewInContext(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect);

	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException, SystemException;

	public boolean isAssetTypeSearchable();

	public boolean isScopeable();

	public boolean isVisible();

	public String render(
		long classPK, RenderRequest renderRequest,
		RenderResponse renderResponse, String template);

	public void startWorkflowInstance(
			long companyId, long groupId, long userId, long classPK,
			Object model, Map<String, Serializable> workflowContext)
		throws PortalException, SystemException;

	public Object updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException, SystemException;

}