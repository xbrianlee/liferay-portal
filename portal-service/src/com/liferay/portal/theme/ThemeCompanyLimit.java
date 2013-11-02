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

package com.liferay.portal.theme;

import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 */
public class ThemeCompanyLimit implements Serializable {

	public ThemeCompanyLimit() {
		_includes = new ArrayList<ThemeCompanyId>();
		_excludes = new ArrayList<ThemeCompanyId>();
	}

	public List<ThemeCompanyId> getExcludes() {
		return _excludes;
	}

	public List<ThemeCompanyId> getIncludes() {
		return _includes;
	}

	public boolean isExcluded(long companyId) {
		return _matches(_excludes, companyId);
	}

	public boolean isIncluded(long companyId) {
		return _matches(_includes, companyId);
	}

	public void setExcludes(List<? extends ThemeCompanyId> excludes) {
		_excludes = (List<ThemeCompanyId>)excludes;
	}

	public void setIncludes(List<? extends ThemeCompanyId> includes) {
		_includes = (List<ThemeCompanyId>)includes;
	}

	private boolean _matches(
		List<ThemeCompanyId> themeCompanyIds, long companyId) {

		for (int i = 0; i < themeCompanyIds.size(); i++) {
			ThemeCompanyId themeCompanyId = themeCompanyIds.get(i);

			String themeCompanyIdValue = themeCompanyId.getValue();

			if (themeCompanyId.isPattern()) {
				Pattern pattern = Pattern.compile(themeCompanyIdValue);
				Matcher matcher = pattern.matcher(String.valueOf(companyId));

				if (matcher.matches()) {
					return true;
				}
			}
			else {
				long themeCompanyIdValueLong = GetterUtil.getLong(
					themeCompanyIdValue);

				if (themeCompanyIdValueLong == companyId) {
					return true;
				}
			}
		}

		return false;
	}

	private List<ThemeCompanyId> _excludes;
	private List<ThemeCompanyId> _includes;

}