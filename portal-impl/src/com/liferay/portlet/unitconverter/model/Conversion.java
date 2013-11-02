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

package com.liferay.portlet.unitconverter.model;

/**
 * @author James Lefeu
 */
public class Conversion {

	public Conversion(int type, int fromId, int toId, double fromValue) {
		_type = type;
		_fromId = fromId;
		_toId = toId;
		_fromValue = fromValue;
	}

	public Conversion(
		int type, int fromId, int toId, double fromValue, double toValue) {

		_type = type;
		_fromId = fromId;
		_toId = toId;
		_fromValue = fromValue;
		_toValue = toValue;
	}

	public int getFromId() {
		return _fromId;
	}

	public double getFromValue() {
		return _fromValue;
	}

	public int getToId() {
		return _toId;
	}

	public double getToValue() {
		return _toValue;
	}

	public int getType() {
		return _type;
	}

	public void setFromValue(double fromValue) {
		_fromValue = fromValue;
	}

	public void setToValue(double toValue) {
		_toValue = toValue;
	}

	private int _fromId;
	private double _fromValue;
	private int _toId;
	private double _toValue;
	private int _type;

}