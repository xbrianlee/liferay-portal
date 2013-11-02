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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portlet.asset.model.AssetVocabulary;

/**
 * @author Juan Fernández
 */
public class AssetCategoryException extends PortalException {

	public static final int AT_LEAST_ONE_CATEGORY = 1;

	public static final int TOO_MANY_CATEGORIES = 2;

	public AssetCategoryException(AssetVocabulary vocabulary, int type) {
		_vocabulary = vocabulary;
		_type = type;
	}

	public int getType() {
		return _type;
	}

	public AssetVocabulary getVocabulary() {
		return _vocabulary;
	}

	private int _type;
	private AssetVocabulary _vocabulary;

}