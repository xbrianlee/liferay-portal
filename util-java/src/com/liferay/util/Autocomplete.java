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

package com.liferay.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class Autocomplete {

	public static JSONArray arrayToJson(String[] array, int max) {
		return arrayToJson(_singleToPairArray(array), max);
	}

	public static JSONArray arrayToJson(String[][] array, int max) {
		if (max <= 0) {
			max = array.length;
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; (i < array.length) && (i < max); i++) {
			String text = array[i][0];
			String value = array[i][1];

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

			jsonObj.put("text", text);
			jsonObj.put("value", value);

			jsonArray.put(jsonObj);
		}

		return jsonArray;
	}

	public static String arrayToXml(String[] array, int max) {
		return arrayToXml(_singleToPairArray(array), max);
	}

	public static String arrayToXml(String[][] array, int max) {
		if (max <= 0) {
			max = array.length;
		}

		StringBundler sb = new StringBundler(array.length * 8 + 3);

		sb.append("<?xml version=\"1.0\"?>");

		sb.append("<ajaxresponse>");

		for (int i = 0; (i < array.length) && (i < max); i++) {
			String text = array[i][0];
			String value = array[i][1];

			sb.append("<item>");
			sb.append("<text><![CDATA[");
			sb.append(text);
			sb.append("]]></text>");
			sb.append("<value><![CDATA[");
			sb.append(value);
			sb.append("]]></value>");
			sb.append("</item>");
		}

		sb.append("</ajaxresponse>");

		return sb.toString();
	}

	public static String[][] listToArray(
		List<?> list, String textParam, String valueParam) {

		String[][] array = new String[list.size()][2];

		for (int i = 0; i < list.size(); i++) {
			Object bean = list.get(i);

			Object text = BeanPropertiesUtil.getObject(bean, textParam);

			if (text == null) {
				text = StringPool.BLANK;
			}

			Object value = BeanPropertiesUtil.getObject(bean, valueParam);

			if (value == null) {
				value = StringPool.BLANK;
			}

			array[i][0] = text.toString();
			array[i][1] = value.toString();
		}

		return array;
	}

	public static JSONArray listToJson(
		List<?> list, String textParam, String valueParam) {

		return arrayToJson(listToArray(list, textParam, valueParam), -1);
	}

	public static String listToXml(
		List<?> list, String textParam, String valueParam) {

		return arrayToXml(listToArray(list, textParam, valueParam), -1);
	}

	private static String[][] _singleToPairArray(String[] array) {
		String[][] pairArray = new String[array.length][2];

		for (int i = 0; i < array.length; i++) {
			pairArray[i][0] = HtmlUtil.escape(array[i]);
			pairArray[i][1] = array[i];
		}

		return pairArray;
	}

}