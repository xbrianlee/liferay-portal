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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.model.CountryConstants;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class CountryImpl extends CountryBaseImpl {

	public CountryImpl() {
	}

	@Override
	public String getName(Locale locale) {
		String name = LanguageUtil.get(
			locale, CountryConstants.NAME_PREFIX + getName());

		if (!name.startsWith(CountryConstants.NAME_PREFIX)) {
			return name;
		}

		return getName();
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	private String _nameCurrentLanguageId;

}