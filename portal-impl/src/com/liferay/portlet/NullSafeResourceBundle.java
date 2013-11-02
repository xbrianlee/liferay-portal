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

package com.liferay.portlet;

import com.liferay.portal.kernel.util.ResourceBundleThreadLocal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Shuyang Zhou
 */
public class NullSafeResourceBundle extends ResourceBundle {

	public NullSafeResourceBundle(ResourceBundle resourceBundle) {
		if (resourceBundle == null) {
			throw new NullPointerException();
		}

		setParent(resourceBundle);
	}

	@Override
	public Enumeration<String> getKeys() {
		return parent.getKeys();
	}

	@Override
	protected Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}

		try {
			return parent.getString(key);
		}
		catch (MissingResourceException mre) {
			if (ResourceBundleThreadLocal.isReplace()) {
				return ResourceBundleUtil.NULL_VALUE;
			}
			else {
				throw mre;
			}
		}
	}

}