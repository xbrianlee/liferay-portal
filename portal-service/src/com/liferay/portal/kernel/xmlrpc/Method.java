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

package com.liferay.portal.kernel.xmlrpc;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public interface Method {

	public Response execute(long companyId) throws XmlRpcException;

	public String getMethodName();

	public String getToken();

	public boolean setArguments(Object[] arguments);

}