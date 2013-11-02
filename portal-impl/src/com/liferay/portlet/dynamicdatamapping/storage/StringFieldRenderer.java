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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.util.DDMImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Bruno Basto
 */
public class StringFieldRenderer extends BaseFieldRenderer {

	@Override
	protected String doRender(Field field, Locale locale) throws Exception {
		String fieldType = getFieldType(field);

		List<String> values = new ArrayList<String>();

		for (Serializable value : field.getValues(locale)) {
			String valueString = String.valueOf(value);

			if (Validator.isNull(valueString)) {
				continue;
			}

			if (fieldType.equals(DDMImpl.TYPE_RADIO) ||
				fieldType.equals(DDMImpl.TYPE_SELECT)) {

				valueString = handleJSON(field, valueString, locale);
			}

			values.add(valueString);
		}

		return StringUtil.merge(values, StringPool.COMMA_AND_SPACE);
	}

	@Override
	protected String doRender(Field field, Locale locale, int valueIndex)
		throws Exception {

		Serializable value = field.getValue(locale, valueIndex);

		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		String fieldType = getFieldType(field);

		if (fieldType.equals(DDMImpl.TYPE_RADIO) ||
			fieldType.equals(DDMImpl.TYPE_SELECT)) {

			return handleJSON(field, String.valueOf(value), locale);
		}

		return String.valueOf(value);
	}

	protected String getFieldType(Field field) throws Exception {
		DDMStructure ddmStructure = field.getDDMStructure();

		return ddmStructure.getFieldType(field.getName());
	}

	protected String handleJSON(Field field, String json, Locale locale)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(json);

		DDMStructure ddmStructure = field.getDDMStructure();

		StringBundler sb = new StringBundler(jsonArray.length() * 2);

		for (int i = 0; i < jsonArray.length(); i++) {
			Map<String, String> fieldsMap = ddmStructure.getFields(
				field.getName(), FieldConstants.VALUE, jsonArray.getString(i),
				LocaleUtil.toLanguageId(locale));

			if (fieldsMap == null) {
				continue;
			}

			sb.append(fieldsMap.get(FieldConstants.LABEL));

			if ((i + 1) < jsonArray.length()) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}
		}

		return sb.toString();
	}

}