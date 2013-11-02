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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

/**
 * @author Alexander Chow
 * @author Sandeep Soni
 * @author Ganesh Ram
 */
public class JDataStoreDB extends FirebirdDB {

	public static DB getInstance() {
		return _instance;
	}

	@Override
	public String buildSQL(String template) throws IOException {
		template = convertTimestamp(template);
		template = replaceTemplate(template, getTemplate());

		template = reword(template);
		template = StringUtil.replace(
			template,
			new String[] {"\\'", "\\\"", "\\\\", "\\n", "\\r"},
			new String[] {"''", "\"", "\\", "\n", "\r"});

		return template;
	}

	protected JDataStoreDB() {
		super(TYPE_JDATASTORE);
	}

	@Override
	protected String getServerName() {
		return "jdatastore";
	}

	@Override
	protected String[] getTemplate() {
		return _JDATASTORE;
	}

	private static final String[] _JDATASTORE = {
		"--", "TRUE", "FALSE", "'1970-01-01'", "current_timestamp", " binary",
		" binary", " boolean", " date", " double", " integer", " bigint",
		" long varchar", " long varchar", " varchar", "", "commit"
	};

	private static JDataStoreDB _instance = new JDataStoreDB();

}