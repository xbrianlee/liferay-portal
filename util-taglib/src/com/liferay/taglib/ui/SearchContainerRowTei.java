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

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * @author Raymond Augé
 */
public class SearchContainerRowTei extends TagExtraInfo {

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData) {
		String className = tagData.getAttributeString("className");

		String indexVar = tagData.getAttributeString("indexVar");

		if (Validator.isNull(indexVar)) {
			indexVar = SearchContainerRowTag.DEFAULT_INDEX_VAR;
		}

		String modelVar = tagData.getAttributeString("modelVar");

		if (Validator.isNull(modelVar)) {
			modelVar = SearchContainerRowTag.DEFAULT_MODEL_VAR;
		}

		String rowVar = tagData.getAttributeString("rowVar");

		if (Validator.isNull(rowVar)) {
			rowVar = SearchContainerRowTag.DEFAULT_ROW_VAR;
		}

		return new VariableInfo[] {
			new VariableInfo(
				indexVar, Integer.class.getName(), true, VariableInfo.NESTED),
			new VariableInfo(modelVar, className, true, VariableInfo.NESTED),
			new VariableInfo(
				rowVar, ResultRow.class.getName(), true, VariableInfo.NESTED)
		};
	}

}