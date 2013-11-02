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

package com.liferay.support.tomcat.startup;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringComparator;

import java.io.File;

import java.util.Arrays;

import org.apache.catalina.startup.HostConfig;

/**
 * <p>
 * Tomcat will always process XML descriptors first, then packaged WARs, and
 * then exploded WARs. However, Tomcat does not have a predictable load order
 * for the XML descriptors or the WARs. It relies on Java's
 * <code>java.io.File.list()</code> implementation which is not predictable.
 * This class overrides several of the deploy methods to ensure that the files
 * are always processed alphabetically (case sensitive).
 * </p>
 *
 * <p>
 * To use this class, modify Tomcat's conf/server.xml. Find the
 * <code>Host</code> element and add the attribute <code>hostConfigClass</code>.
 * </p>
 *
 * <p>
 * See http://issues.liferay.com/browse/LEP-2346.
 * </p>
 *
 * <p>
 * See <code>org.apache.catalina.startup.HostConfig</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PortalHostConfig extends HostConfig {

	public PortalHostConfig() {
		super();
	}

	@Override
	protected void deployDescriptors(File configBase, String[] files) {
		super.deployDescriptors(configBase, sortFiles(files));
	}

	@Override
	protected void deployDirectories(File appBase, String[] files) {
		super.deployDirectories(appBase, sortFiles(files));
	}

	@Override
	protected void deployWARs(File appBase, String[] files) {
		super.deployWARs(appBase, sortFiles(files));
	}

	protected String[] sortFiles(String[] files) {
		Arrays.sort(files, new StringComparator(true, true));

		if (_log.isDebugEnabled()) {
			_log.debug("Sort " + files.length + " files");

			for (int i = 0; i < files.length; i++) {
				_log.debug("File " + i + " " + files[i]);
			}
		}

		return files;
	}

	private static Log _log = LogFactoryUtil.getLog(PortalHostConfig.class);

}