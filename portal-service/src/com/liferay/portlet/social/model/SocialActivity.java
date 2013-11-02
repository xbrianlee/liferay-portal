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

package com.liferay.portlet.social.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the SocialActivity service. Represents a row in the &quot;SocialActivity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityModel
 * @see com.liferay.portlet.social.model.impl.SocialActivityImpl
 * @see com.liferay.portlet.social.model.impl.SocialActivityModelImpl
 * @generated
 */
@ProviderType
public interface SocialActivity extends SocialActivityModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.social.model.impl.SocialActivityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getExtraDataValue(java.lang.String key)
		throws com.liferay.portal.kernel.json.JSONException;

	public java.lang.String getExtraDataValue(java.lang.String key,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.json.JSONException;

	public boolean isClassName(java.lang.String className);

	public void setAssetEntry(
		com.liferay.portlet.asset.model.AssetEntry assetEntry);

	public void setExtraDataValue(java.lang.String key, java.lang.String value)
		throws com.liferay.portal.kernel.json.JSONException;
}