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

package com.liferay.portlet.currencyconverter;

import com.liferay.portlet.currencyconverter.model.Currency;
import com.liferay.portlet.currencyconverter.util.CurrencyUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

/**
 * @author Brian Wing Shun Chan
 */
public class CurrencyPreferencesValidator implements PreferencesValidator {

	@Override
	public void validate(PortletPreferences preferences)
		throws ValidatorException {

		List<String> badSymbols = new ArrayList<String>();

		String[] symbols = preferences.getValues("symbols", new String[0]);

		for (int i = 0; i < symbols.length; i++) {
			Currency currency = CurrencyUtil.getCurrency(symbols[i]);

			if (currency == null) {
				badSymbols.add(symbols[i]);
			}
		}

		if (badSymbols.size() > 0) {
			throw new ValidatorException(
				"Failed to retrieve symbols", badSymbols);
		}
	}

}