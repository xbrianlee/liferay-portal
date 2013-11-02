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

package com.liferay.portal.sharepoint.methods;

import com.liferay.portal.sharepoint.SharepointException;
import com.liferay.portal.sharepoint.SharepointRequest;

/**
 * @author Bruno Farache
 */
public interface Method {

	public static final String GET = "GET";

	public static final String POST = "POST";

	public String getMethodName();

	public String getRootPath(SharepointRequest sharepointRequest);

	public void process(SharepointRequest sharepointRequest)
		throws SharepointException;

}