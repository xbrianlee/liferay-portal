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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.tools.DBLoader;
import com.liferay.portal.util.InitUtil;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.Properties;

/**
 * @author Tina Tian
 * @author Shuyang Zhou
 */
public class TestSampleSQLBuilder {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		Reader reader = null;

		try {
			Properties properties = new SortedProperties();

			reader = new FileReader(args[0]);

			properties.load(reader);

			DataFactory dataFactory = new DataFactory(properties);

			new SampleSQLBuilder(properties, dataFactory);

			String sqlDir = properties.getProperty("sql.dir");
			String outputDir = properties.getProperty("sample.sql.output.dir");

			loadHypersonic(sqlDir, outputDir);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	protected static void loadHypersonic(String sqlDir, String outputDir)
		throws Exception {

		Class.forName("org.hsqldb.jdbcDriver");

		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(
				"jdbc:hsqldb:mem:testSampleSQLBuilderDB;shutdown=true", "sa",
				"");

			DBLoader.loadHypersonic(
				connection, sqlDir + "/portal/portal-hypersonic.sql");
			DBLoader.loadHypersonic(
				connection, sqlDir + "/indexes/indexes-hypersonic.sql");
			DBLoader.loadHypersonic(
				connection, outputDir + "/sample-hypersonic.sql");

			statement = connection.createStatement();

			statement.execute("SHUTDOWN COMPACT");
		}
		finally {
			DataAccess.cleanUp(connection, statement);
		}
	}

}