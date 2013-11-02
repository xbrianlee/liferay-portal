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

package com.liferay.portal.security.ldap;

import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

/**
 * @author Amos Fong
 * @author Brian Wing Shun Chan
 */
public class Modifications {

	public static Modifications getInstance() {
		return new Modifications();
	}

	public ModificationItem addItem(BasicAttribute basicAttribute) {
		return addItem(DirContext.REPLACE_ATTRIBUTE, basicAttribute);
	}

	public ModificationItem addItem(
		int modificationOp, BasicAttribute basicAttribute) {

		ModificationItem modificationItem = new ModificationItem(
			modificationOp, basicAttribute);

		_modificationItems.add(modificationItem);

		return modificationItem;
	}

	public ModificationItem addItem(
		int modificationOp, String id, Object value) {

		BasicAttribute basicAttribute = new BasicAttribute(id);

		if (value != null) {
			basicAttribute.add(value);
		}

		return addItem(modificationOp, basicAttribute);
	}

	public ModificationItem addItem(
		int modificationOp, String id, String value) {

		BasicAttribute basicAttribute = new BasicAttribute(id);

		if (Validator.isNotNull(value)) {
			basicAttribute.add(value);
		}

		return addItem(modificationOp, basicAttribute);
	}

	public ModificationItem addItem(String id, Object value) {
		return addItem(DirContext.REPLACE_ATTRIBUTE, id, value);
	}

	public ModificationItem addItem(String id, String value) {
		return addItem(DirContext.REPLACE_ATTRIBUTE, id, value);
	}

	public ModificationItem[] getItems() {
		return _modificationItems.toArray(
			new ModificationItem[_modificationItems.size()]);
	}

	private Modifications() {
	}

	private List<ModificationItem> _modificationItems =
		new ArrayList<ModificationItem>();

}