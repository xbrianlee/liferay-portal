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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.lang.reflect.Method;

import java.net.URI;
import java.net.URL;

import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class JasperVersionDetector {

	public static String getJasperVersion() {
		return _instance._jasperVersion;
	}

	public static boolean hasJspServletDependantsMap() {
		return _instance._jspServletDependantsMap;
	}

	private JasperVersionDetector() {
		_initializeJasperVersion();
		_initializeJspServletDependantsMap();
	}

	private void _initializeJasperVersion() {
		try {
			Class<?> clazz = getClass();

			URL url = clazz.getResource(
				"/org/apache/jasper/JasperException.class");

			if (url == null) {
				return;
			}

			String path = url.getPath();

			int pos = path.indexOf(CharPool.EXCLAMATION);

			if (pos == -1) {
				return;
			}

			URI jarFileURI = new URI(path.substring(0, pos));

			JarFile jarFile = new JarFile(new File(jarFileURI));

			Manifest manifest = jarFile.getManifest();

			Attributes attributes = manifest.getMainAttributes();

			if (attributes.containsKey(Attributes.Name.SPECIFICATION_VERSION)) {
				_jasperVersion = GetterUtil.getString(
					attributes.getValue(Attributes.Name.SPECIFICATION_VERSION));

				if (_isValidJasperVersion(_jasperVersion)) {
					return;
				}
			}

			if (attributes.containsKey(
					Attributes.Name.IMPLEMENTATION_VERSION)) {

				_jasperVersion = GetterUtil.getString(
					attributes.get(Attributes.Name.IMPLEMENTATION_VERSION));

				if (_isValidJasperVersion(_jasperVersion)) {
					return;
				}
			}

			Attributes.Name bundleVersionAttributesName = new Attributes.Name(
				"Bundle-Version");

			if (attributes.containsKey(bundleVersionAttributesName)) {
				_jasperVersion = GetterUtil.getString(
					attributes.get(bundleVersionAttributesName));

				if (_isValidJasperVersion(_jasperVersion)) {
					return;
				}

				_jasperVersion = StringPool.BLANK;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private void _initializeJspServletDependantsMap() {
		try {
			Class<?> clazz = Class.forName(
				"org.apache.jasper.servlet.JspServletWrapper");

			Method method = ReflectionUtil.getDeclaredMethod(
				clazz, "getDependants");

			Class<?> returnType = method.getReturnType();

			_jspServletDependantsMap = Map.class.isAssignableFrom(returnType);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private boolean _isValidJasperVersion(String jasperVersion) {
		if (Validator.isNull(jasperVersion) ||
			!Validator.isDigit(jasperVersion.charAt(0))) {

			return false;
		}
		else {
			return true;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		JasperVersionDetector.class);

	private static JasperVersionDetector _instance =
		new JasperVersionDetector();

	private String _jasperVersion = StringPool.BLANK;
	private boolean _jspServletDependantsMap;

}