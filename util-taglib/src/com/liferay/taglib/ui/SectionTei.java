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

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * @author Brian Wing Shun Chan
 */
public class SectionTei extends TagExtraInfo {

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData) {
		return _variableInfo;
	}

	private static VariableInfo[] _variableInfo = new VariableInfo[] {
		new VariableInfo(
			"sectionParam", String.class.getName(), true, VariableInfo.NESTED),
		new VariableInfo(
			"sectionName", String.class.getName(), true, VariableInfo.NESTED),
		new VariableInfo(
			"sectionSelected", Boolean.class.getName(), true,
			VariableInfo.NESTED),
		new VariableInfo(
			"sectionScroll", String.class.getName(), true, VariableInfo.NESTED),
		new VariableInfo(
			"sectionRedirectParams", String.class.getName(), true,
			VariableInfo.NESTED)
	};

}