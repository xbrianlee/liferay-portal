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

package com.liferay.portal.language;

import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.ResourceBundleThreadLocal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class LiferayResourceBundle extends ResourceBundle {

	public LiferayResourceBundle(InputStream inputStream, String charsetName)
		throws IOException {

		this(null, inputStream, charsetName);
	}

	public LiferayResourceBundle(
			ResourceBundle parentResourceBundle, InputStream inputStream,
			String charsetName)
		throws IOException {

		setParent(parentResourceBundle);

		_map = new HashMap<String, String>();

		Properties properties = PropertiesUtil.load(inputStream, charsetName);

		LanguageResources.fixValues(_map, properties);
	}

	public LiferayResourceBundle(String string, String charsetName)
		throws IOException {

		_map = new HashMap<String, String>();

		Properties properties = PropertiesUtil.load(string, charsetName);

		LanguageResources.fixValues(_map, properties);
	}

	@Override
	public Enumeration<String> getKeys() {
		final Set<String> keys = _map.keySet();

		final Enumeration<String> parentKeys =
			(parent == null) ? null : parent.getKeys();

		final Iterator<String> itr = keys.iterator();

		return new Enumeration<String>() {
			String next = null;

			@Override
			public boolean hasMoreElements() {
				if (next == null) {
					if (itr.hasNext()) {
						next = itr.next();
					}
					else if (parentKeys != null) {
						while ((next == null) && parentKeys.hasMoreElements()) {
							next = parentKeys.nextElement();

							if (keys.contains(next)) {
								next = null;
							}
						}
					}
				}

				if (next != null) {
					return true;
				}
				else {
					return false;
				}
			}

			@Override
			public String nextElement() {
				if (hasMoreElements()) {
					String result = next;

					next = null;

					return result;
				}
				else {
					throw new NoSuchElementException();
				}
			}

		};
	}

	@Override
	public Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}

		String value = _map.get(key);

		if ((value == null) && ResourceBundleThreadLocal.isReplace()) {
			if (parent != null) {
				try {
					value = parent.getString(key);
				}
				catch (MissingResourceException mre) {
				}
			}

			if (value == null) {
				value = ResourceBundleUtil.NULL_VALUE;
			}
		}

		return value;
	}

	private Map<String, String> _map;

}