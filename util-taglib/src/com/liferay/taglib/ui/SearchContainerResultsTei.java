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

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * @author Raymond Augé
 */
public class SearchContainerResultsTei extends TagExtraInfo {

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData) {
		String resultsVar = tagData.getAttributeString("resultsVar");

		if (Validator.isNull(resultsVar)) {
			resultsVar = SearchContainer.DEFAULT_RESULTS_VAR;
		}

		String totalVar = tagData.getAttributeString("totalVar");

		if (Validator.isNull(totalVar)) {
			totalVar = SearchContainer.DEFAULT_DEPRECATED_TOTAL_VAR;
		}

		return new VariableInfo[] {
			new VariableInfo(
				resultsVar, List.class.getName(), true, VariableInfo.AT_BEGIN),
			new VariableInfo(
				totalVar, Integer.class.getName(), true, VariableInfo.AT_BEGIN)
		};
	}

}