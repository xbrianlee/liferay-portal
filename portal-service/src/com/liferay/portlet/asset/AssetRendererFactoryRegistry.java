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

package com.liferay.portlet.asset;

import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.util.List;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
public interface AssetRendererFactoryRegistry {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getAssetRendererFactories(
	 *             long)}
	 */
	public List<AssetRendererFactory> getAssetRendererFactories();

	public List<AssetRendererFactory> getAssetRendererFactories(long companyId);

	public AssetRendererFactory getAssetRendererFactoryByClassName(
		String className);

	public AssetRendererFactory getAssetRendererFactoryByType(String type);

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getClassNameIds( long)}
	 */
	public long[] getClassNameIds();

	public long[] getClassNameIds(long companyId);

	public void register(AssetRendererFactory assetRendererFactory);

	public void unregister(AssetRendererFactory assetRendererFactory);

}