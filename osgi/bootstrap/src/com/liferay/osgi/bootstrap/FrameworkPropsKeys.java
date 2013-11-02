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

package com.liferay.osgi.bootstrap;

/**
 * @author Miguel Pastor
 * @author Raymond Augé
 */
public interface FrameworkPropsKeys {

	public static final String FELIX_FILEINSTALL_DIR = "felix.fileinstall.dir";

	public static final String FELIX_FILEINSTALL_LOG_LEVEL =
		"felix.fileinstall.log.level";

	public static final String FELIX_FILEINSTALL_POLL =
		"felix.fileinstall.poll";

	public static final String FELIX_FILEINSTALL_TMPDIR =
		"felix.fileinstall.tmpdir";

	public static final String OSGI_FRAMEWORK = "osgi.framework";

	public static final String OSGI_INSTALL_AREA = "osgi.install.area";

}