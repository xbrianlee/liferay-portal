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

package com.liferay.portlet;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class ResourceResponseFactory {

	public static ResourceResponseImpl create(
			ResourceRequestImpl resourceRequestImpl,
			HttpServletResponse response, String portletName, long companyId)
		throws Exception {

		return create(resourceRequestImpl, response, portletName, companyId, 0);
	}

	public static ResourceResponseImpl create(
			ResourceRequestImpl resourceRequestImpl,
			HttpServletResponse response, String portletName, long companyId,
			long plid)
		throws Exception {

		ResourceResponseImpl resourceResponseImpl = new ResourceResponseImpl();

		resourceResponseImpl.init(
			resourceRequestImpl, response, portletName, companyId, plid);

		return resourceResponseImpl;
	}

}