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

package com.liferay.portlet.asset.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the AssetCategory service. Represents a row in the &quot;AssetCategory&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryModel
 * @see com.liferay.portlet.asset.model.impl.AssetCategoryImpl
 * @see com.liferay.portlet.asset.model.impl.AssetCategoryModelImpl
 * @generated
 */
@ProviderType
public interface AssetCategory extends AssetCategoryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.asset.model.impl.AssetCategoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetCategory, String> UUID_ACCESSOR = new Accessor<AssetCategory, String>() {
			@Override
			public String get(AssetCategory assetCategory) {
				return assetCategory.getUuid();
			}
		};

	public static final Accessor<AssetCategory, Long> CATEGORY_ID_ACCESSOR = new Accessor<AssetCategory, Long>() {
			@Override
			public Long get(AssetCategory assetCategory) {
				return assetCategory.getCategoryId();
			}
		};

	public static final Accessor<AssetCategory, String> NAME_ACCESSOR = new Accessor<AssetCategory, String>() {
			@Override
			public String get(AssetCategory assetCategory) {
				return assetCategory.getName();
			}
		};

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getAncestors()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isRootCategory();
}