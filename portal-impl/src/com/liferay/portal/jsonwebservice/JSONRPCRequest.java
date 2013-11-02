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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import jodd.servlet.ServletUtil;

/**
 * @author Igor Spasic
 */
public class JSONRPCRequest {

	public static JSONRPCRequest detectJSONRPCRequest(
		HttpServletRequest request) {

		try {
			String requestBody = ServletUtil.readRequestBody(request);

			if (Validator.isNull(requestBody) ||
				!requestBody.startsWith(StringPool.OPEN_CURLY_BRACE) ||
				!requestBody.endsWith(StringPool.CLOSE_CURLY_BRACE)) {

				return null;
			}

			JSONDeserializer<HashMap<Object, Object>> jsonDeserializer =
				JSONFactoryUtil.createJSONDeserializer();

			jsonDeserializer.use(null, HashMap.class);
			jsonDeserializer.use("parameters", HashMap.class);

			HashMap<Object, Object> requestBodyMap =
				jsonDeserializer.deserialize(requestBody);

			JSONRPCRequest jsonrpcRequest = new JSONRPCRequest();

			jsonrpcRequest._id = (Integer)requestBodyMap.get("id");
			jsonrpcRequest._jsonrpc = (String)requestBodyMap.get("jsonrpc");
			jsonrpcRequest._method = (String)requestBodyMap.get("method");
			jsonrpcRequest._parameters = (Map<String, ?>)requestBodyMap.get(
				"params");

			if (Validator.isNull(jsonrpcRequest._method)) {
				return null;
			}

			return jsonrpcRequest;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse JSON RPC request", e);
			}

			return null;
		}
	}

	public Integer getId() {
		return _id;
	}

	public String getJsonrpc() {
		return _jsonrpc;
	}

	public String getMethod() {
		return _method;
	}

	public String getParameter(String name) {
		Object value = _parameters.get(name);

		if (value != null) {
			return String.valueOf(value);
		}
		else {
			return null;
		}
	}

	public Set<String> getParameterNames() {
		return _parameters.keySet();
	}

	public void setId(Integer id) {
		_id = id;
	}

	public void setJsonrpc(String jsonrpc) {
		_jsonrpc = jsonrpc;
	}

	public void setMethod(String method) {
		_method = method;
	}

	public void setParameters(Map<String, ?> parameters) {
		_parameters = parameters;
	}

	private static Log _log = LogFactoryUtil.getLog(JSONRPCRequest.class);

	private Integer _id;
	private String _jsonrpc;
	private String _method;
	private Map<String, ?> _parameters;

}