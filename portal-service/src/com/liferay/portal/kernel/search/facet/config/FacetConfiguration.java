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

package com.liferay.portal.kernel.search.facet.config;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Raymond Augé
 */
public class FacetConfiguration {

	public String getClassName() {
		return _className;
	}

	public JSONObject getData() {
		if (_dataJSONObject == null) {
			_dataJSONObject = JSONFactoryUtil.createJSONObject();
		}

		return _dataJSONObject;
	}

	public String getDisplayStyle() {
		return _displayStyle;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public String getLabel() {
		return _label;
	}

	public String getOrder() {
		if (_order == null) {
			return "OrderHitsDesc";
		}

		return _order;
	}

	public double getWeight() {
		return _weight;
	}

	public boolean isStatic() {
		return _static;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setDataJSONObject(JSONObject dataJSONObject) {
		_dataJSONObject = dataJSONObject;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setOrder(String order) {
		_order = order;
	}

	public void setStatic(boolean isStatic) {
		_static = isStatic;
	}

	public void setWeight(double weight) {
		_weight = weight;
	}

	private String _className;
	private JSONObject _dataJSONObject;
	private String _displayStyle;
	private String _fieldName;
	private String _label;
	private String _order;
	private boolean _static;
	private double _weight;

}