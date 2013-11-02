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

package com.liferay.portlet.currencyconverter.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class Currency implements Serializable {

	public static final String DEFAULT_FROM = "USD";

	public static final String DEFAULT_TO = "EUR";

	public Currency(String symbol, double rate) {
		_symbol = symbol;
		_rate = rate;
	}

	public String getFromSymbol() {
		if ((_symbol != null) && (_symbol.length() == 6)) {
			return _symbol.substring(0, 3);
		}

		return DEFAULT_FROM;
	}

	public double getRate() {
		return _rate;
	}

	public String getSymbol() {
		return _symbol;
	}

	public String getToSymbol() {
		if ((_symbol != null) && (_symbol.length() == 6)) {
			return _symbol.substring(3, 6);
		}

		return DEFAULT_TO;
	}

	private double _rate;
	private String _symbol;

}