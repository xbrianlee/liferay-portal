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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Shuyang Zhou
 */
public class ClusterException extends PortalException {

	public ClusterException() {
		super();
	}

	public ClusterException(String msg) {
		super(msg);
	}

	public ClusterException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ClusterException(Throwable cause) {
		super(cause);
	}

}