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

package com.liferay.portal.xmlrpc;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xmlrpc.Fault;
import com.liferay.portal.kernel.xmlrpc.Response;
import com.liferay.portal.kernel.xmlrpc.Success;
import com.liferay.portal.kernel.xmlrpc.XmlRpc;
import com.liferay.portal.kernel.xmlrpc.XmlRpcException;
import com.liferay.portal.util.PropsValues;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public class XmlRpcImpl implements XmlRpc {

	@Override
	public Fault createFault(int code, String description) {
		return new FaultImpl(code, description);
	}

	@Override
	public Success createSuccess(String description) {
		return new SuccessImpl(description);
	}

	@Override
	public Response executeMethod(
			String url, String methodName, Object[] arguments)
		throws XmlRpcException {

		try {
			return doExecuteMethod(url, methodName, arguments);
		}
		catch (Exception e) {
			throw new XmlRpcException(e);
		}
	}

	protected Response doExecuteMethod(
			String url, String methodName, Object[] arguments)
		throws Exception {

		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler();

			sb.append("XML-RPC invoking ");
			sb.append(methodName);
			sb.append(" ");

			if (arguments != null) {
				for (int i = 0; i < arguments.length; i++) {
					sb.append(arguments[i]);

					if (i < (arguments.length - 1)) {
						sb.append(", ");
					}
				}
			}

			_log.debug(sb.toString());
		}

		String requestXML = XmlRpcParser.buildMethod(methodName, arguments);

		Http.Options options = new Http.Options();

		if (_HTTP_HEADER_VERSION_VERBOSITY_DEFAULT) {
		}
		else if (_HTTP_HEADER_VERSION_VERBOSITY_PARTIAL) {
			options.addHeader(HttpHeaders.USER_AGENT, ReleaseInfo.getName());
		}
		else {
			options.addHeader(
				HttpHeaders.USER_AGENT, ReleaseInfo.getServerInfo());
		}

		options.setBody(requestXML, ContentTypes.TEXT_XML, StringPool.UTF8);
		options.setLocation(url);
		options.setPost(true);

		String responseXML = HttpUtil.URLtoString(options);

		return XmlRpcParser.parseResponse(responseXML);
	}

	private static final boolean _HTTP_HEADER_VERSION_VERBOSITY_DEFAULT =
		StringUtil.equalsIgnoreCase(
			PropsValues.HTTP_HEADER_VERSION_VERBOSITY, ReleaseInfo.getName());

	private static final boolean _HTTP_HEADER_VERSION_VERBOSITY_PARTIAL =
		StringUtil.equalsIgnoreCase(
			PropsValues.HTTP_HEADER_VERSION_VERBOSITY, "partial");

	private static Log _log = LogFactoryUtil.getLog(XmlRpcImpl.class);

}