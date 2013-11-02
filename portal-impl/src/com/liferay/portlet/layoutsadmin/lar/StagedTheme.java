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

package com.liferay.portlet.layoutsadmin.lar;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.impl.ThemeImpl;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * @author Mate Thurzo
 */
public class StagedTheme extends ThemeImpl implements StagedModel {

	public StagedTheme(Theme theme) {
		super(theme.getThemeId());
	}

	@Override
	public Object clone() {
		ThemeImpl themeImpl = new ThemeImpl(getThemeId());

		return new StagedTheme(themeImpl);
	}

	@Override
	public long getCompanyId() {
		return CompanyThreadLocal.getCompanyId();
	}

	@Override
	public Date getCreateDate() {
		return new Date();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public Class<?> getModelClass() {
		return StagedTheme.class;
	}

	@Override
	public String getModelClassName() {
		return StagedTheme.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return new Date();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return getThemeId();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(StagedTheme.class);
	}

	@Override
	public String getUuid() {
		return StringPool.BLANK;
	}

	@Override
	public void setCompanyId(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCreateDate(Date date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setModifiedDate(Date date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setUuid(String uuid) {
		throw new UnsupportedOperationException();
	}

}