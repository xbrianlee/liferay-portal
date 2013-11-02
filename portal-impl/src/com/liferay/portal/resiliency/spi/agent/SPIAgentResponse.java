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

package com.liferay.portal.resiliency.spi.agent;

import com.liferay.portal.kernel.resiliency.PortalResiliencyException;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.Direction;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.servlet.MetaInfoCacheServletResponse;
import com.liferay.portal.kernel.servlet.MetaInfoCacheServletResponse.MetaData;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Layout;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.io.IOException;
import java.io.Serializable;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Shuyang Zhou
 */
public class SPIAgentResponse extends SPIAgentSerializable {

	public SPIAgentResponse(String servletContextName) {
		super(servletContextName);
	}

	public void captureRequestSessionAttributes(HttpServletRequest request) {
		distributedRequestAttributes = extractDistributedRequestAttributes(
			request, Direction.RESPONSE);

		SPIAgentRequest spiAgentRequest = (SPIAgentRequest)request.getAttribute(
			WebKeys.SPI_AGENT_REQUEST);

		Map<String, Serializable> originalSessionAttributes =
			spiAgentRequest.getOriginalSessionAttributes();

		Map<String, Serializable> newSessionAttributes =
			extractSessionAttributes(request);

		Set<String> removedSessionAttributeNames =
			originalSessionAttributes.keySet();

		removedSessionAttributeNames.removeAll(newSessionAttributes.keySet());

		deltaSessionAttributes = new HashMap<String, Serializable>(
			newSessionAttributes);

		for (String removedSessionAttributeName :
				removedSessionAttributeNames) {

			deltaSessionAttributes.put(removedSessionAttributeName, null);
		}

		captureThreadLocals();
	}

	public void captureResponse(
			HttpServletRequest request,
			BufferCacheServletResponse bufferCacheServletResponse)
		throws IOException {

		Boolean portalResiliencyAction = (Boolean)request.getAttribute(
			WebKeys.PORTAL_RESILIENCY_ACTION);

		if (portalResiliencyAction != Boolean.TRUE) {
			portalResiliencyResponse = false;

			return;
		}

		portalResiliencyResponse = true;

		metaData = bufferCacheServletResponse.getMetaData();

		byteData = null;

		if (bufferCacheServletResponse.isByteMode()) {
			ByteBuffer byteBuffer = bufferCacheServletResponse.getByteBuffer();

			if (byteBuffer.remaining() > 0) {
				if (byteBuffer.hasArray()) {
					byte[] byteArray = byteBuffer.array();

					if (byteBuffer.remaining() == byteArray.length) {
						byteData = byteArray;
					}
				}

				if (byteData == null) {
					byteData = new byte[byteBuffer.remaining()];

					byteBuffer.get(byteData);
				}
			}
		}

		stringData = null;

		if (!bufferCacheServletResponse.isCharMode()) {
			return;
		}

		String content = bufferCacheServletResponse.getString();

		if (content.length() == 0) {
			return;
		}

		if (ParamUtil.get(
				request, "portalResiliencyPortletShowFooter",
				PropsValues.PORTAL_RESILIENCY_PORTLET_SHOW_FOOTER)) {

			int index = content.lastIndexOf("</div>");

			if (index > 0) {
				StringBundler sb = new StringBundler(6);

				sb.append(content.substring(0, index));
				sb.append("<div class=\"alert alert-info\"><strong>");
				sb.append("This portlet is from SPI ");
				sb.append(PortalUtil.getPortalPort(false));
				sb.append("</strong></div>");
				sb.append(content.substring(index));

				content = sb.toString();
			}
		}

		stringData = content;
	}

	public void populate(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalResiliencyException {

		if (exception != null) {
			throw new PortalResiliencyException("SPI exception", exception);
		}

		if (!portalResiliencyResponse) {
			return;
		}

		String typeSettings = (String)distributedRequestAttributes.remove(
			WebKeys.SPI_AGENT_LAYOUT_TYPE_SETTINGS);

		if (typeSettings != null) {
			Layout layout = (Layout)request.getAttribute(WebKeys.LAYOUT);

			layout.setTypeSettings(typeSettings);
		}

		for (Map.Entry<String, Serializable> entry :
				distributedRequestAttributes.entrySet()) {

			request.setAttribute(entry.getKey(), entry.getValue());
		}

		HttpSession session = request.getSession();

		for (Map.Entry<String, Serializable> entry :
				deltaSessionAttributes.entrySet()) {

			session.setAttribute(entry.getKey(), entry.getValue());
		}

		try {
			MetaInfoCacheServletResponse.finishResponse(metaData, response);

			if (byteData != null) {
				ServletResponseUtil.write(response, ByteBuffer.wrap(byteData));
			}

			if (stringData != null) {
				ServletResponseUtil.write(
					response, CharBuffer.wrap(stringData));
			}
		}
		catch (IOException ioe) {
			throw new PortalResiliencyException(ioe);
		}

		restoreThreadLocals();
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	protected byte[] byteData;
	protected Map<String, Serializable> deltaSessionAttributes;
	protected Map<String, Serializable> distributedRequestAttributes;
	protected Exception exception;
	protected MetaData metaData;
	protected boolean portalResiliencyResponse;
	protected String stringData;

}