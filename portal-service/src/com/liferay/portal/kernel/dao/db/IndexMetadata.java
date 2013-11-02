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

package com.liferay.portal.kernel.dao.db;

/**
 * @author James Lefeu
 * @author Peter Shin
 */
public class IndexMetadata extends Index {

	public IndexMetadata(
		String indexName, String tableName, boolean unique,
		String specification, String createSQL, String dropSQL) {

		super(indexName, tableName, unique);

		_specification = specification;
		_createSQL = createSQL;
		_dropSQL = dropSQL;
	}

	public String getCreateSQL() {
		return _createSQL;
	}

	public String getDropSQL() {
		return _dropSQL;
	}

	public String getSpecification() {
		return _specification;
	}

	private String _createSQL;
	private String _dropSQL;
	private String _specification;

}