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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Charles May
 */
public class DiscussionTag extends IncludeTag {

	public void setAssetEntryVisible(boolean assetEntryVisible) {
		_assetEntryVisible = assetEntryVisible;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setFormAction(String formAction) {
		_formAction = formAction;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setHideControls(boolean hideControls) {
		_hideControls = hideControls;
	}

	public void setPermissionClassName(String permissionClassName) {
		_permissionClassName = permissionClassName;
	}

	public void setPermissionClassPK(long permissionClassPK) {
		_permissionClassPK = permissionClassPK;
	}

	public void setRatingsEnabled(boolean ratingsEnabled) {
		_ratingsEnabled = ratingsEnabled;
	}

	public void setRedirect(String redirect) {
		_redirect = redirect;
	}

	/**
	 * @deprecated As of 6.2.0, with no direct replacement
	 */
	public void setSubject(String subject) {
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	protected void cleanUp() {
		_assetEntryVisible = true;
		_className = null;
		_classPK = 0;
		_formAction = null;
		_formName = "fm";
		_hideControls = false;
		_permissionClassName = null;
		_permissionClassPK = 0;
		_ratingsEnabled = true;
		_redirect = null;
		_userId = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		String permissionClassName = _permissionClassName;

		if (Validator.isNull(permissionClassName)) {
			permissionClassName = _className;
		}

		long permissionClassPK = _permissionClassPK;

		if (permissionClassPK == 0) {
			permissionClassPK = _classPK;
		}

		request.setAttribute(
			"liferay-ui:discussion:assetEntryVisible",
			String.valueOf(_assetEntryVisible));
		request.setAttribute("liferay-ui:discussion:className", _className);
		request.setAttribute(
			"liferay-ui:discussion:classPK", String.valueOf(_classPK));
		request.setAttribute("liferay-ui:discussion:formAction", _formAction);
		request.setAttribute("liferay-ui:discussion:formName", _formName);
		request.setAttribute(
			"liferay-ui:discussion:hideControls",
			String.valueOf(_hideControls));
		request.setAttribute(
			"liferay-ui:discussion:permissionClassName", permissionClassName);
		request.setAttribute(
			"liferay-ui:discussion:permissionClassPK",
			String.valueOf(permissionClassPK));
		request.setAttribute(
			"liferay-ui:discussion:ratingsEnabled",
			String.valueOf(_ratingsEnabled));
		request.setAttribute("liferay-ui:discussion:redirect", _redirect);
		request.setAttribute(
			"liferay-ui:discussion:userId", String.valueOf(_userId));
	}

	private static final String _PAGE = "/html/taglib/ui/discussion/page.jsp";

	private boolean _assetEntryVisible = true;
	private String _className;
	private long _classPK;
	private String _formAction;
	private String _formName = "fm";
	private boolean _hideControls;
	private String _permissionClassName;
	private long _permissionClassPK;
	private boolean _ratingsEnabled = true;
	private String _redirect;
	private long _userId;

}