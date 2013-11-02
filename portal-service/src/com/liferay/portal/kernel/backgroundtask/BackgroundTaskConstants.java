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

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Daniel Kocsis
 * @author Eduardo Garcia
 */
public class BackgroundTaskConstants {

	public static final String LABEL_CANCELLED = "cancelled";

	public static final String LABEL_FAILED = "failed";

	public static final String LABEL_IN_PROGRESS = "in-progress";

	public static final String LABEL_NEW = "new";

	public static final String LABEL_QUEUED = "queued";

	public static final String LABEL_SUCCESSFUL = "successful";

	public static final int STATUS_CANCELLED = 5;

	public static final int STATUS_FAILED = 2;

	public static final int STATUS_IN_PROGRESS = 1;

	public static final int STATUS_NEW = 0;

	public static final int STATUS_QUEUED = 4;

	public static final int STATUS_SUCCESSFUL = 3;

	public static String getStatusCssClass(int status) {
		if (status == STATUS_CANCELLED) {
			return "label-info";
		}
		else if (status == STATUS_FAILED) {
			return "label-important";
		}
		else if (status == STATUS_IN_PROGRESS) {
			return "label-warning";
		}
		else if ((status == BackgroundTaskConstants.STATUS_NEW) ||
				 (status == BackgroundTaskConstants.STATUS_QUEUED)) {

			return "label-info";
		}
		else if (status == STATUS_SUCCESSFUL) {
			return "label-success";
		}

		return StringPool.BLANK;
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_CANCELLED) {
			return LABEL_CANCELLED;
		}
		else if (status == STATUS_FAILED) {
			return LABEL_FAILED;
		}
		else if (status == STATUS_IN_PROGRESS) {
			return LABEL_IN_PROGRESS;
		}
		else if (status == STATUS_NEW) {
			return LABEL_NEW;
		}
		else if (status == STATUS_QUEUED) {
			return LABEL_QUEUED;
		}
		else if (status == STATUS_SUCCESSFUL) {
			return LABEL_SUCCESSFUL;
		}
		else {
			return StringPool.BLANK;
		}
	}

}