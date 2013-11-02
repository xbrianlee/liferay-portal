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

package com.liferay.portlet.flags.messaging;

import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class FlagsRequest implements Serializable {

	public FlagsRequest() {
	}

	public FlagsRequest(
		String className, long classPK, String reporterEmailAddress,
		long reportedUserId, String contentTitle, String contentURL,
		String reason, ServiceContext serviceContext) {

		_className = className;
		_classPK = classPK;
		_reporterEmailAddress = reporterEmailAddress;
		_reportedUserId = reportedUserId;
		_contentTitle = contentTitle;
		_contentURL = contentURL;
		_reason = reason;
		_serviceContext = serviceContext;
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public String getComments() {
		return _comments;
	}

	public String getContentTitle() {
		return _contentTitle;
	}

	public String getContentURL() {
		return _contentURL;
	}

	public String getReason() {
		return _reason;
	}

	public long getReportedUserId() {
		return _reportedUserId;
	}

	public String getReporterEmailAddress() {
		return _reporterEmailAddress;
	}

	public ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public void setContentTitle(String contentTitle) {
		_contentTitle = contentTitle;
	}

	public void setContentURL(String contentURL) {
		_contentURL = contentURL;
	}

	public void setReason(String reason) {
		this._reason = reason;
	}

	public void setReportedUserId(long reportedUserId) {
		_reportedUserId = reportedUserId;
	}

	public void setReporterEmailAddress(String reporterEmailAddress) {
		_reporterEmailAddress = reporterEmailAddress;
	}

	public void setServiceContext(ServiceContext serviceContext) {
		_serviceContext = serviceContext;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{className=");
		sb.append(_className);
		sb.append(", classPK=");
		sb.append(_classPK);
		sb.append(", comments=");
		sb.append(_comments);
		sb.append(", contentTitle=");
		sb.append(_contentTitle);
		sb.append(", contentURL=");
		sb.append(_contentURL);
		sb.append(", reason=");
		sb.append(_reason);
		sb.append(", reportedUserId=");
		sb.append(_reportedUserId);
		sb.append(", reporterEmailAddress=");
		sb.append(_reporterEmailAddress);
		sb.append(", serviceContext=");
		sb.append(_serviceContext);
		sb.append("}");

		return sb.toString();
	}

	private String _className;
	private long _classPK;
	private String _comments;
	private String _contentTitle;
	private String _contentURL;
	private String _reason;
	private long _reportedUserId;
	private String _reporterEmailAddress;
	private ServiceContext _serviceContext;

}