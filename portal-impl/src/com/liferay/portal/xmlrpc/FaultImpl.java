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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xmlrpc.Fault;
import com.liferay.portal.kernel.xmlrpc.XmlRpcException;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public class FaultImpl implements Fault {

	public FaultImpl(int code, String description) {
		_code = code;
		_description = description;
	}

	@Override
	public int getCode() {
		return _code;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public String toString() {
		return "XML-RPC fault " + _code + " " + _description;
	}

	@Override
	public String toXml() throws XmlRpcException {
		StringBundler sb = new StringBundler(17);

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

		sb.append("<methodResponse>");
		sb.append("<fault>");
		sb.append("<value>");
		sb.append("<struct>");
		sb.append("<member>");
		sb.append("<name>faultCode</name>");
		sb.append(XmlRpcParser.wrapValue(_code));
		sb.append("</member>");
		sb.append("<member>");
		sb.append("<name>faultString</name>");
		sb.append(XmlRpcParser.wrapValue(_description));
		sb.append("</member>");
		sb.append("</struct>");
		sb.append("</value>");
		sb.append("</fault>");
		sb.append("</methodResponse>");

		return sb.toString();
	}

	private int _code;
	private String _description;

}