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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.servlet.taglib.aui.ToolTag;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.base.BasePanelTag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class PanelTag extends BasePanelTag {

	public void addToolTag(ToolTag toolTag) {
		if (_toolTags == null) {
			_toolTags = new ArrayList<ToolTag>();
		}

		_toolTags.add(toolTag);
	}

	public List<ToolTag> getToolTags() {
		return _toolTags;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		if (_toolTags != null) {
			for (ToolTag toolTag : _toolTags) {
				toolTag.cleanUp();
			}

			_toolTags = null;
		}
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		String id = getId();

		if (Validator.isNull(id)) {
			id = StringUtil.randomId();
		}

		setNamespacedAttribute(request, "id", id);
		setNamespacedAttribute(request, "toolTags", _toolTags);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private List<ToolTag> _toolTags;

}