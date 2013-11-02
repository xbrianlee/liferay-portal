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

package com.liferay.portal.resiliency.spi.action;

import com.liferay.portal.kernel.portlet.ActionResult;
import com.liferay.portal.kernel.portlet.PortletContainer;
import com.liferay.portal.kernel.portlet.PortletContainerUtil;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.resiliency.spi.agent.SPIAgentRequest;
import com.liferay.portal.resiliency.spi.agent.SPIAgentResponse;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.util.WebKeys;

import java.util.List;

import javax.portlet.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Shuyang Zhou
 */
public class PortalResiliencyAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SPIAgentRequest spiAgentRequest = (SPIAgentRequest)request.getAttribute(
			WebKeys.SPI_AGENT_REQUEST);

		HttpSession session = request.getSession();

		spiAgentRequest.populateSessionAttributes(session);

		PrincipalThreadLocal.setPassword(
			(String)session.getAttribute(WebKeys.USER_PASSWORD));

		try {
			_doExecute(request, response);
		}
		finally {
			SPIAgentResponse spiAgentResponse =
				(SPIAgentResponse)request.getAttribute(
					WebKeys.SPI_AGENT_RESPONSE);

			spiAgentResponse.captureRequestSessionAttributes(request);

			request.setAttribute(
				WebKeys.PORTAL_RESILIENCY_ACTION, Boolean.TRUE);
		}

		return null;
	}

	private void _doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		PortletContainer portletContainer =
			PortletContainerUtil.getPortletContainer();

		Portlet portlet = (Portlet)request.getAttribute(
			WebKeys.SPI_AGENT_PORTLET);

		String portletId = ParamUtil.getString(request, "p_p_id");

		if (portletId.equals(portlet.getPortletId())) {
			portletContainer.preparePortlet(request, portlet);
		}

		SPIAgent.Lifecycle lifecycle = (SPIAgent.Lifecycle)request.getAttribute(
			WebKeys.SPI_AGENT_LIFECYCLE);

		switch (lifecycle) {
			case ACTION :

				// Action and event requests can make a transient change to a
				// layout's type settings. The fix should be done via a regular
				// request attribute, but to avoid a massive refactor, the SPI
				// simply sends back the modified layout type settings to the
				// MPI.

				Layout requestLayout = (Layout)request.getAttribute(
					WebKeys.LAYOUT);

				String typeSettings = requestLayout.getTypeSettings();

				ActionResult actionResult = portletContainer.processAction(
					request, response, portlet);

				String newTypeSettings = requestLayout.getTypeSettings();

				if (!newTypeSettings.equals(typeSettings)) {
					request.setAttribute(
						WebKeys.SPI_AGENT_LAYOUT_TYPE_SETTINGS,
						newTypeSettings);
				}

				request.setAttribute(
					WebKeys.SPI_AGENT_ACTION_RESULT, actionResult);

				break;

			case EVENT :
				requestLayout = (Layout)request.getAttribute(WebKeys.LAYOUT);

				typeSettings = requestLayout.getTypeSettings();

				Layout layout = (Layout)request.getAttribute(
					WebKeys.SPI_AGENT_LAYOUT);

				Event event = (Event)request.getAttribute(
					WebKeys.SPI_AGENT_EVENT);

				List<Event> events = portletContainer.processEvent(
					request, response, portlet, layout, event);

				newTypeSettings = requestLayout.getTypeSettings();

				if (!newTypeSettings.equals(typeSettings)) {
					request.setAttribute(
						WebKeys.SPI_AGENT_LAYOUT_TYPE_SETTINGS,
						newTypeSettings);
				}

				request.setAttribute(WebKeys.SPI_AGENT_EVENT_RESULT, events);

				break;

			case RENDER :
				portletContainer.render(request, response, portlet);

				break;

			case RESOURCE :
				portletContainer.serveResource(request, response, portlet);

				break;

			default :
				throw new IllegalArgumentException(
					"Unkown lifecycle " + lifecycle);
		}
	}

}