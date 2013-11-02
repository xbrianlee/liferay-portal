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

package com.liferay.portlet.softwarecatalog.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portlet.softwarecatalog.DuplicateProductVersionDirectDownloadURLException;
import com.liferay.portlet.softwarecatalog.NoSuchProductVersionException;
import com.liferay.portlet.softwarecatalog.ProductVersionChangeLogException;
import com.liferay.portlet.softwarecatalog.ProductVersionDownloadURLException;
import com.liferay.portlet.softwarecatalog.ProductVersionFrameworkVersionException;
import com.liferay.portlet.softwarecatalog.ProductVersionNameException;
import com.liferay.portlet.softwarecatalog.UnavailableProductVersionDirectDownloadURLException;
import com.liferay.portlet.softwarecatalog.model.SCProductVersion;
import com.liferay.portlet.softwarecatalog.service.SCProductVersionServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jorge Ferrer
 */
public class EditProductVersionAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateProductVersion(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteProductVersion(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof NoSuchProductVersionException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				setForward(actionRequest, "portlet.software_catalog.error");
			}
			else if (e instanceof
						DuplicateProductVersionDirectDownloadURLException ||
					 e instanceof ProductVersionChangeLogException ||
					 e instanceof ProductVersionDownloadURLException ||
					 e instanceof ProductVersionFrameworkVersionException ||
					 e instanceof ProductVersionNameException ||
					 e instanceof
						UnavailableProductVersionDirectDownloadURLException) {

				SessionErrors.add(actionRequest, e.getClass());
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		try {
			ActionUtil.getProductVersion(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchProductVersionException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return actionMapping.findForward(
					"portlet.software_catalog.error");
			}
			else {
				throw e;
			}
		}

		return actionMapping.findForward(
			getForward(
				renderRequest,
				"portlet.software_catalog.edit_product_version"));
	}

	protected void deleteProductVersion(ActionRequest actionRequest)
		throws Exception {

		long productVersionId = ParamUtil.getLong(
			actionRequest, "productVersionId");

		SCProductVersionServiceUtil.deleteProductVersion(productVersionId);
	}

	protected void updateProductVersion(ActionRequest actionRequest)
		throws Exception {

		long productVersionId = ParamUtil.getLong(
			actionRequest, "productVersionId");

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		String version = ParamUtil.getString(actionRequest, "version");
		String changeLog = ParamUtil.getString(actionRequest, "changeLog");
		String downloadPageURL = ParamUtil.getString(
			actionRequest, "downloadPageURL");
		String directDownloadURL = ParamUtil.getString(
			actionRequest, "directDownloadURL");
		boolean testDirectDownloadURL = ParamUtil.getBoolean(
			actionRequest, "testDirectDownloadURL");
		boolean repoStoreArtifact = ParamUtil.getBoolean(
			actionRequest, "repoStoreArtifact");

		long[] frameworkVersionIds = ParamUtil.getLongValues(
			actionRequest, "frameworkVersions");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			SCProductVersion.class.getName(), actionRequest);

		if (productVersionId <= 0) {

			// Add product version

			SCProductVersionServiceUtil.addProductVersion(
				productEntryId, version, changeLog, downloadPageURL,
				directDownloadURL, testDirectDownloadURL, repoStoreArtifact,
				frameworkVersionIds, serviceContext);
		}
		else {

			// Update product version

			SCProductVersionServiceUtil.updateProductVersion(
				productVersionId, version, changeLog, downloadPageURL,
				directDownloadURL, testDirectDownloadURL, repoStoreArtifact,
				frameworkVersionIds);
		}
	}

}