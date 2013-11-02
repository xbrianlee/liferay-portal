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

package com.liferay.portal.tools.servicebuilder;

/**
 * @author Glenn Powell
 * @author Brian Wing Shun Chan
 */
public class EntityMapping {

	public EntityMapping(String table, String entity1, String entity2) {
		_table = table;
		_entities[0] = entity1;
		_entities[1] = entity2;
	}

	public String getEntity(int index) {
		try {
			return _entities[index];
		}
		catch (Exception e) {
			return null;
		}
	}

	public String getTable() {
		return _table;
	}

	private String[] _entities = new String[2];
	private String _table;

}