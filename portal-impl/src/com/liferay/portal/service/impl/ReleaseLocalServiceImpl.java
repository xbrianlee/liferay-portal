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

package com.liferay.portal.service.impl;

import com.liferay.portal.NoSuchReleaseException;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Release;
import com.liferay.portal.model.ReleaseConstants;
import com.liferay.portal.service.base.ReleaseLocalServiceBaseImpl;
import com.liferay.portal.util.PropsUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ReleaseLocalServiceImpl extends ReleaseLocalServiceBaseImpl {

	@Override
	public Release addRelease(String servletContextName, int buildNumber)
		throws SystemException {

		Release release = null;

		if (servletContextName.equals(
				ReleaseConstants.DEFAULT_SERVLET_CONTEXT_NAME)) {

			release = releasePersistence.create(ReleaseConstants.DEFAULT_ID);
		}
		else {
			long releaseId = counterLocalService.increment();

			release = releasePersistence.create(releaseId);
		}

		Date now = new Date();

		release.setCreateDate(now);
		release.setModifiedDate(now);
		release.setServletContextName(servletContextName);
		release.setBuildNumber(buildNumber);

		if (servletContextName.equals(
				ReleaseConstants.DEFAULT_SERVLET_CONTEXT_NAME)) {

			release.setTestString(ReleaseConstants.TEST_STRING);
		}

		releasePersistence.update(release);

		return release;
	}

	@Override
	public void createTablesAndPopulate() throws SystemException {
		try {
			if (_log.isInfoEnabled()) {
				_log.info("Create tables and populate with default data");
			}

			DB db = DBFactoryUtil.getDB();

			db.runSQLTemplate("portal-tables.sql", false);
			db.runSQLTemplate("portal-data-common.sql", false);
			db.runSQLTemplate("portal-data-counter.sql", false);
			db.runSQLTemplate("portal-data-release.sql", false);
			db.runSQLTemplate("indexes.sql", false);
			db.runSQLTemplate("sequences.sql", false);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SystemException(e);
		}
	}

	@Override
	public Release fetchRelease(String servletContextName)
		throws SystemException {

		if (Validator.isNull(servletContextName)) {
			throw new IllegalArgumentException("Servlet context name is null");
		}

		Release release = null;

		if (servletContextName.equals(
				ReleaseConstants.DEFAULT_SERVLET_CONTEXT_NAME)) {

			release = releasePersistence.fetchByPrimaryKey(
				ReleaseConstants.DEFAULT_ID);
		}
		else {
			release = releasePersistence.fetchByServletContextName(
				servletContextName);
		}

		return release;
	}

	@Override
	public int getBuildNumberOrCreate()
		throws PortalException, SystemException {

		// Get release build number

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(_GET_BUILD_NUMBER);

			ps.setLong(1, ReleaseConstants.DEFAULT_ID);

			rs = ps.executeQuery();

			if (rs.next()) {
				int buildNumber = rs.getInt("buildNumber");

				if (_log.isDebugEnabled()) {
					_log.debug("Build number " + buildNumber);
				}

				DB db = DBFactoryUtil.getDB();

				try {
					db.runSQL("alter table Release_ add state_ INTEGER");
				}
				catch (Exception e) {
					if (_log.isDebugEnabled()) {
						_log.debug(e.getMessage());
					}
				}

				testSupportsStringCaseSensitiveQuery();

				return buildNumber;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		// Create tables and populate with default data

		if (GetterUtil.getBoolean(
				PropsUtil.get(PropsKeys.SCHEMA_RUN_ENABLED))) {

			releaseLocalService.createTablesAndPopulate();

			testSupportsStringCaseSensitiveQuery();

			Release release = fetchRelease(
				ReleaseConstants.DEFAULT_SERVLET_CONTEXT_NAME);

			return release.getBuildNumber();
		}
		else {
			throw new NoSuchReleaseException(
				"The database needs to be populated");
		}
	}

	@Override
	public Release updateRelease(
			long releaseId, int buildNumber, Date buildDate, boolean verified)
		throws PortalException, SystemException {

		Release release = releasePersistence.findByPrimaryKey(releaseId);

		release.setModifiedDate(new Date());
		release.setBuildNumber(buildNumber);
		release.setBuildDate(buildDate);
		release.setVerified(verified);

		releasePersistence.update(release);

		return release;
	}

	protected void testSupportsStringCaseSensitiveQuery()
		throws SystemException {

		DB db = DBFactoryUtil.getDB();

		int count = testSupportsStringCaseSensitiveQuery(
			ReleaseConstants.TEST_STRING);

		if (count == 0) {
			try {
				db.runSQL(
					"alter table Release_ add testString VARCHAR(1024) null");
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e.getMessage());
				}
			}

			try {
				db.runSQL(
					"update Release_ set testString = '" +
						ReleaseConstants.TEST_STRING + "'");
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e.getMessage());
				}
			}

			count = testSupportsStringCaseSensitiveQuery(
				ReleaseConstants.TEST_STRING);
		}

		if (count == 0) {
			throw new SystemException(
				"Release_ table was not initialized properly");
		}

		count = testSupportsStringCaseSensitiveQuery(
			StringUtil.toUpperCase(ReleaseConstants.TEST_STRING));

		if (count == 0) {
			db.setSupportsStringCaseSensitiveQuery(true);
		}
		else {
			db.setSupportsStringCaseSensitiveQuery(false);
		}
	}

	protected int testSupportsStringCaseSensitiveQuery(String testString) {
		int count = 0;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(_TEST_DATABASE_STRING_CASE_SENSITIVITY);

			ps.setLong(1, ReleaseConstants.DEFAULT_ID);
			ps.setString(2, testString);

			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return count;
	}

	private static final String _GET_BUILD_NUMBER =
		"select buildNumber from Release_ where releaseId = ?";

	private static final String _TEST_DATABASE_STRING_CASE_SENSITIVITY =
		"select count(*) from Release_ where releaseId = ? and testString = ?";

	private static Log _log = LogFactoryUtil.getLog(
		ReleaseLocalServiceImpl.class);

}