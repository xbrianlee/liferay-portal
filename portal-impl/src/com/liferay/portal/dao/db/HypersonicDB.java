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

package com.liferay.portal.dao.db;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

/**
 * @author Alexander Chow
 * @author Sandeep Soni
 * @author Ganesh Ram
 */
public class HypersonicDB extends BaseDB {

	public static DB getInstance() {
		return _instance;
	}

	@Override
	public String buildSQL(String template) throws IOException {
		template = convertTimestamp(template);
		template = replaceTemplate(template, getTemplate());

		template = reword(template);
		template = StringUtil.replace(template, "\\'", "''");

		return template;
	}

	protected HypersonicDB() {
		super(TYPE_HYPERSONIC);
	}

	@Override
	protected String buildCreateFileContent(
		String sqlDir, String databaseName, int population) {

		return null;
	}

	@Override
	protected String getServerName() {
		return "hypersonic";
	}

	@Override
	protected String[] getTemplate() {
		return _HYPERSONIC;
	}

	@Override
	protected String reword(String data) throws IOException {
		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(data));

		StringBundler sb = new StringBundler();

		String line = null;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			if (line.startsWith(ALTER_COLUMN_NAME)) {
				String[] template = buildColumnNameTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter column @old-column@ rename to " +
						"@new-column@;",
					REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(ALTER_COLUMN_TYPE)) {
				String[] template = buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter column @old-column@ @type@ " +
						"@nullable@;",
					REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(ALTER_TABLE_NAME)) {
				String[] template = buildTableNameTokens(line);

				line = StringUtil.replace(
					"alter table @old-table@ rename to @new-table@;",
					RENAME_TABLE_TEMPLATE, template);
			}
			else if (line.contains(DROP_INDEX)) {
				String[] tokens = StringUtil.split(line, ' ');

				line = StringUtil.replace(
					"drop index @index@;", "@index@", tokens[2]);
			}

			sb.append(line);
			sb.append("\n");
		}

		unsyncBufferedReader.close();

		return sb.toString();
	}

	private static final String[] _HYPERSONIC = {
		"//", "true", "false", "'1970-01-01 00:00:00'", "now()", " blob",
		" blob", " bit", " timestamp", " double", " int", " bigint",
		" longvarchar", " longvarchar", " varchar", "", "commit"
	};

	private static HypersonicDB _instance = new HypersonicDB();

}