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

package com.liferay.portlet.expando.util.comparator;

import com.liferay.portal.security.permission.comparator.ModelResourceComparator;
import com.liferay.portlet.expando.model.CustomAttributesDisplay;

import java.io.Serializable;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class CustomAttributesDisplayComparator
	implements Comparator<CustomAttributesDisplay>, Serializable {

	public CustomAttributesDisplayComparator(Locale locale) {
		_modelResourceComparator = new ModelResourceComparator(locale);
	}

	@Override
	public int compare(
		CustomAttributesDisplay customAttributesDisplay1,
		CustomAttributesDisplay customAttributesDisplay2) {

		return _modelResourceComparator.compare(
			customAttributesDisplay1.getClassName(),
			customAttributesDisplay2.getClassName());
	}

	private ModelResourceComparator _modelResourceComparator;

}