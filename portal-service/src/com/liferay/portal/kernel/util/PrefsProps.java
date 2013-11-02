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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Properties;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 */
public interface PrefsProps {

	public boolean getBoolean(long companyId, String name)
		throws SystemException;

	public boolean getBoolean(long companyId, String name, boolean defaultValue)
		throws SystemException;

	public boolean getBoolean(
		PortletPreferences preferences, long companyId, String name);

	public boolean getBoolean(
		PortletPreferences preferences, long companyId, String name,
		boolean defaultValue);

	public boolean getBoolean(String name) throws SystemException;

	public boolean getBoolean(String name, boolean defaultValue)
		throws SystemException;

	public String getContent(long companyId, String name)
		throws SystemException;

	public String getContent(
		PortletPreferences preferences, long companyId, String name);

	public String getContent(String name) throws SystemException;

	public double getDouble(long companyId, String name)
		throws SystemException;

	public double getDouble(long companyId, String name, double defaultValue)
		throws SystemException;

	public double getDouble(
		PortletPreferences preferences, long companyId, String name);

	public double getDouble(
		PortletPreferences preferences, long companyId, String name,
		double defaultValue);

	public double getDouble(String name) throws SystemException;

	public double getDouble(String name, double defaultValue)
		throws SystemException;

	public int getInteger(long companyId, String name)
		throws SystemException;

	public int getInteger(long companyId, String name, int defaultValue)
		throws SystemException;

	public int getInteger(
		PortletPreferences preferences, long companyId, String name);

	public int getInteger(
		PortletPreferences preferences, long companyId, String name,
		int defaultValue);

	public int getInteger(String name) throws SystemException;

	public int getInteger(String name, int defaultValue)
		throws SystemException;

	public long getLong(long companyId, String name)
		throws SystemException;

	public long getLong(long companyId, String name, long defaultValue)
		throws SystemException;

	public long getLong(
		PortletPreferences preferences, long companyId, String name);

	public long getLong(
		PortletPreferences preferences, long companyId, String name,
		long defaultValue);

	public long getLong(String name) throws SystemException;

	public long getLong(String name, long defaultValue)
		throws SystemException;

	public PortletPreferences getPreferences() throws SystemException;

	public PortletPreferences getPreferences(boolean readOnly)
		throws SystemException;

	public PortletPreferences getPreferences(long companyId)
		throws SystemException;

	public PortletPreferences getPreferences(long companyId, boolean readOnly)
		throws SystemException;

	public Properties getProperties(
		PortletPreferences preferences, long companyId, String prefix,
		boolean removePrefix);

	public Properties getProperties(String prefix, boolean removePrefix)
		throws SystemException;

	public short getShort(long companyId, String name)
		throws SystemException;

	public short getShort(long companyId, String name, short defaultValue)
		throws SystemException;

	public short getShort(
		PortletPreferences preferences, long companyId, String name);

	public short getShort(
		PortletPreferences preferences, long companyId, String name,
		short defaultValue);

	public short getShort(String name) throws SystemException;

	public short getShort(String name, short defaultValue)
		throws SystemException;

	public String getString(long companyId, String name)
		throws SystemException;

	public String getString(long companyId, String name, String defaultValue)
		throws SystemException;

	public String getString(
		PortletPreferences preferences, long companyId, String name);

	public String getString(
		PortletPreferences preferences, long companyId, String name,
		boolean defaultValue);

	public String getString(
		PortletPreferences preferences, long companyId, String name,
		double defaultValue);

	public String getString(
		PortletPreferences preferences, long companyId, String name,
		int defaultValue);

	public String getString(
		PortletPreferences preferences, long companyId, String name,
		long defaultValue);

	public String getString(
		PortletPreferences preferences, long companyId, String name,
		short defaultValue);

	public String getString(
		PortletPreferences preferences, long companyId, String name,
		String defaultValue);

	public String getString(String name) throws SystemException;

	public String getString(String name, String defaultValue)
		throws SystemException;

	public String[] getStringArray(
			long companyId, String name, String delimiter)
		throws SystemException;

	public String[] getStringArray(
			long companyId, String name, String delimiter,
			String[] defaultValue)
		throws SystemException;

	public String[] getStringArray(
		PortletPreferences preferences, long companyId, String name,
		String delimiter);

	public String[] getStringArray(
		PortletPreferences preferences, long companyId, String name,
		String delimiter, String[] defaultValue);

	public String[] getStringArray(String name, String delimiter)
		throws SystemException;

	public String[] getStringArray(
			String name, String delimiter, String[] defaultValue)
		throws SystemException;

	public String getStringFromNames(long companyId, String... names)
		throws SystemException;

}