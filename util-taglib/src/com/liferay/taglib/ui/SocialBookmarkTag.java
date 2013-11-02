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

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author David Truong
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SocialBookmarkTag extends IncludeTag {

	public void setContentId(String contentId) {
		_contentId = contentId;
	}

	public void setTarget(String target) {
		_target = target;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		_contentId = null;
		_target = null;
		_title = null;
		_type = null;
		_url = null;
	}

	@Override
	protected String getPage() {
		String[] socialTypes = PropsUtil.getArray(
			PropsKeys.SOCIAL_BOOKMARK_TYPES);

		if (ArrayUtil.contains(socialTypes, _type)) {
			if (Validator.isNotNull(_jspPath)) {
				return _jspPath;
			}
			else {
				return _PAGE;
			}
		}
		else {
			return null;
		}
	}

	protected String getPostUrl() {
		Map<String, String> vars = new HashMap<String, String>();

		vars.put("liferay:social-bookmark:title", HttpUtil.encodeURL(_title));
		vars.put("liferay:social-bookmark:url", _url);

		String postUrl = PropsUtil.get(
			PropsKeys.SOCIAL_BOOKMARK_POST_URL, new Filter(_type, vars));

		return postUrl;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		String jspPath = _jspPaths.get(_type);

		if (jspPath == null) {
			jspPath = PropsUtil.get(
				PropsKeys.SOCIAL_BOOKMARK_JSP, new Filter(_type));

			_jspPaths.put(_type, jspPath);
		}

		_jspPath = jspPath;

		if (Validator.isNull(_jspPath)) {
			request.setAttribute(
				"liferay-ui:social-bookmark:postUrl", getPostUrl());
		}

		request.setAttribute(
			"liferay-ui:social-bookmark:contentId", _contentId);
		request.setAttribute("liferay-ui:social-bookmark:target", _target);
		request.setAttribute("liferay-ui:social-bookmark:title", _title);
		request.setAttribute("liferay-ui:social-bookmark:type", _type);
		request.setAttribute("liferay-ui:social-bookmark:url", _url);
	}

	private static final String _PAGE =
		"/html/taglib/ui/social_bookmark/page.jsp";

	private static Map<String, String> _jspPaths =
		new HashMap<String, String>();

	private String _contentId;
	private String _jspPath;
	private String _target;
	private String _title;
	private String _type;
	private String _url;

}