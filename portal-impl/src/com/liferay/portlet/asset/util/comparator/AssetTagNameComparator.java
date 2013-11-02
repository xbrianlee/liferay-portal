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

package com.liferay.portlet.asset.util.comparator;

import com.liferay.portlet.asset.model.AssetTag;

import java.util.Comparator;

/**
 * @author Juan Fernández
 */
public class AssetTagNameComparator implements Comparator<AssetTag> {

	public AssetTagNameComparator() {
		this(true, false);
	}

	public AssetTagNameComparator(boolean ascending, boolean caseSensitive) {
		_ascending = ascending;
		_caseSensitive = caseSensitive;
	}

	@Override
	public int compare(AssetTag assetTag1, AssetTag assetTag2) {
		String name1 = assetTag1.getName();
		String name2 = assetTag2.getName();

		int value = 0;

		if (_caseSensitive) {
			value = name1.compareTo(name2);
		}
		else {
			value = name1.compareToIgnoreCase(name2);
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;
	private boolean _caseSensitive;

}