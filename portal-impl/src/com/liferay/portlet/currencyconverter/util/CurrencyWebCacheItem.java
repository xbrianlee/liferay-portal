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

package com.liferay.portlet.currencyconverter.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portlet.currencyconverter.model.Currency;

import java.util.StringTokenizer;

/**
 * @author Brian Wing Shun Chan
 */
public class CurrencyWebCacheItem implements WebCacheItem {

	public CurrencyWebCacheItem(String symbol) {
		_symbol = symbol;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		String symbol = _symbol;
		double rate = 0.0;

		try {
			if (symbol.length() == 6) {
				String fromSymbol = symbol.substring(0, 3);
				String toSymbol = symbol.substring(3, 6);

				if (!CurrencyUtil.isCurrency(fromSymbol) ||
					!CurrencyUtil.isCurrency(toSymbol)) {

					throw new WebCacheException(symbol);
				}
			}
			else if (symbol.length() == 3) {
				if (!CurrencyUtil.isCurrency(symbol)) {
					throw new WebCacheException(symbol);
				}
			}
			else {
				throw new WebCacheException(symbol);
			}

			String text = HttpUtil.URLtoString(
				"http://finance.yahoo.com/d/quotes.csv?s=" +
					symbol + "=X&f=sl1d1t1c1ohgv&e=.csv");

			StringTokenizer st = new StringTokenizer(text, StringPool.COMMA);

			// Skip symbol

			st.nextToken();

			rate = GetterUtil.getDouble(
				st.nextToken().replace('"', ' ').trim());
		}
		catch (Exception e) {
			throw new WebCacheException(e);
		}

		return new Currency(symbol, rate);
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private String _symbol;

}