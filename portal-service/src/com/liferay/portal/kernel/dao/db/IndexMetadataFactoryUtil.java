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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author James Lefeu
 * @author Peter Shin
 */
public class IndexMetadataFactoryUtil {

	public static IndexMetadata createIndexMetadata(
		boolean unique, String tableName, String... columnNames) {

		String specification = _getSpecification(tableName, columnNames);

		String indexName = _getIndexName(specification);

		StringBundler sb = new StringBundler(5);

		if (!unique) {
			sb.append("create ");
		}
		else {
			sb.append("create unique ");
		}

		sb.append("index ");
		sb.append(indexName);
		sb.append(" on ");
		sb.append(specification);

		String createSQL = sb.toString();

		sb.setIndex(0);

		sb.append("drop index ");
		sb.append(indexName);
		sb.append(" on ");
		sb.append(tableName);

		String dropSQL = sb.toString();

		return new IndexMetadata(
			indexName, tableName, unique, specification, createSQL, dropSQL);
	}

	private static String _getIndexName(String specification) {
		String specificationHash = StringUtil.toHexString(
			specification.hashCode());

		specificationHash = StringUtil.toUpperCase(specificationHash);

		return _INDEX_NAME_PREFIX.concat(specificationHash);
	}

	private static String _getSpecification(
		String tableName, String[] columnNames) {

		StringBundler sb = new StringBundler(6);

		sb.append(tableName);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);

		if (ArrayUtil.isNotEmpty(columnNames)) {
			sb.append(
				StringUtil.merge(columnNames, StringPool.COMMA_AND_SPACE));
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);
		sb.append(StringPool.SEMICOLON);

		return sb.toString();
	}

	private static final String _INDEX_NAME_PREFIX = "IX_";

}