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

import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mika Koivisto
 */
public class PortletActionAdapter extends PortletAction {

	public PortletActionAdapter(StrutsPortletAction strutsPortletAction) {
		_strutsPortletAction = strutsPortletAction;
	}

	@Override
	public void processAction(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		StrutsPortletAction originalStrutsPortletAction = null;

		if (_originalPortletAction != null) {
			originalStrutsPortletAction = new StrutsPortletActionAdapter(
				_originalPortletAction, actionMapping, actionForm);
		}

		_strutsPortletAction.processAction(
			originalStrutsPortletAction, portletConfig, actionRequest,
			actionResponse);
	}

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		StrutsPortletAction originalStrutsPortletAction = null;

		if (_originalPortletAction != null) {
			originalStrutsPortletAction = new StrutsPortletActionAdapter(
				_originalPortletAction, actionMapping, actionForm);
		}

		String forward = _strutsPortletAction.render(
			originalStrutsPortletAction, portletConfig, renderRequest,
			renderResponse);

		if (Validator.isNull(forward)) {
			return null;
		}

		ActionForward actionForward = actionMapping.findForward(forward);

		if (actionForward == null) {
			actionForward = new ActionForward(forward);
		}

		return actionForward;
	}

	@Override
	public void serveResource(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		StrutsPortletAction originalStrutsPortletAction = null;

		if (_originalPortletAction != null) {
			originalStrutsPortletAction = new StrutsPortletActionAdapter(
				_originalPortletAction, actionMapping, actionForm);
		}

		_strutsPortletAction.serveResource(
			originalStrutsPortletAction, portletConfig, resourceRequest,
			resourceResponse);
	}

	public void setOriginalPortletAction(PortletAction portletAction) {
		_originalPortletAction = portletAction;
	}

	@Override
	protected boolean isCheckMethodOnProcessAction() {
		if (_originalPortletAction != null) {
			return _originalPortletAction.isCheckMethodOnProcessAction();
		}
		else {
			return super.isCheckMethodOnProcessAction();
		}
	}

	private PortletAction _originalPortletAction;
	private StrutsPortletAction _strutsPortletAction;

}