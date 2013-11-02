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

import com.liferay.portal.kernel.util.DiffResult;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bruno Farache
 */
public class DiffTag extends IncludeTag {

	public void setDiffResults(List<DiffResult>[] diffResults) {
		_diffResults = diffResults;
	}

	public void setSourceName(String sourceName) {
		_sourceName = sourceName;
	}

	public void setTargetName(String targetName) {
		_targetName = targetName;
	}

	@Override
	protected void cleanUp() {
		_diffResults = null;
		_sourceName = null;
		_targetName = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:diff:diffResults", _diffResults);
		request.setAttribute("liferay-ui:diff:sourceName", _sourceName);
		request.setAttribute("liferay-ui:diff:targetName", _targetName);
	}

	private static final String _PAGE = "/html/taglib/ui/diff/page.jsp";

	private List<DiffResult>[] _diffResults;
	private String _sourceName;
	private String _targetName;

}