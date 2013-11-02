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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class Fields implements Iterable<Field>, Serializable {

	public boolean contains(String name) {
		return _fieldsMap.containsKey(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Fields)) {
			return false;
		}

		Fields fields = (Fields)obj;

		if (Validator.equals(_fieldsMap, fields._fieldsMap)) {
			return true;
		}

		return false;
	}

	public Field get(String name) {
		return _fieldsMap.get(name);
	}

	public Set<Locale> getAvailableLocales() {
		Set<Locale> availableLocales = new HashSet<Locale>();

		for (Field field : _fieldsMap.values()) {
			if (field.isPrivate()) {
				continue;
			}

			for (Locale availableLocale : field.getAvailableLocales()) {
				availableLocales.add(availableLocale);
			}
		}

		return availableLocales;
	}

	public Locale getDefaultLocale() {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		Iterator<Field> itr = iterator();

		if (itr.hasNext()) {
			Field field = itr.next();

			defaultLocale = field.getDefaultLocale();
		}

		return defaultLocale;
	}

	public Set<String> getNames() {
		return _fieldsMap.keySet();
	}

	@Override
	public Iterator<Field> iterator() {
		return iterator(false);
	}

	public Iterator<Field> iterator(boolean includePrivateFields) {
		return iterator(null, includePrivateFields);
	}

	public Iterator<Field> iterator(
		Comparator<Field> comparator, boolean includePrivateFields) {

		Collection<Field> fieldsCollection = _fieldsMap.values();

		List<Field> fieldsList = new ArrayList<Field>();

		Iterator<Field> itr = fieldsCollection.iterator();

		while (itr.hasNext()) {
			Field field = itr.next();

			if (!includePrivateFields && field.isPrivate()) {
				continue;
			}

			fieldsList.add(field);
		}

		if (comparator != null) {
			Collections.sort(fieldsList, comparator);
		}

		return fieldsList.iterator();
	}

	public void put(Field field) {
		_fieldsMap.put(field.getName(), field);
	}

	public Field remove(String name) {
		return _fieldsMap.remove(name);
	}

	private Map<String, Field> _fieldsMap = new HashMap<String, Field>();

}