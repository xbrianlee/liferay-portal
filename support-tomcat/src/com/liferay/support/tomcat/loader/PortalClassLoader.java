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

package com.liferay.support.tomcat.loader;

import org.apache.catalina.loader.WebappClassLoader;

/**
 * <p>
 * See sample-struts-liferay-portlet.war. Add META-INF/context.xml to any WAR
 * and set the loaderClass attribute to reference this class. This will allow
 * that WAR to use the portal's class loader.
 * </p>
 *
 * <p>
 * See http://issues.liferay.com/browse/LEP-2346.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PortalClassLoader extends WebappClassLoader {

	public PortalClassLoader(ClassLoader parent) {
		super(PortalClassLoaderFactory.getClassLoader());
	}

}