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

package com.liferay.portal.aspectj;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLClassLoader;

import java.security.ProtectionDomain;

import java.util.Arrays;

import org.aspectj.bridge.AbortException;

/**
 * @author Shuyang Zhou
 */
public class WeavingClassLoader extends URLClassLoader {

	public WeavingClassLoader(
		URL[] urls, Class<?>[] aspectClasses, File dumpDir) {

		super(urls, null);

		_dumpDir = dumpDir;

		_urlWeavingAdaptor = new URLWeavingAdaptor(urls, aspectClasses);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String resourcePath = name.replace('.', '/') + ".class";

		InputStream inputStream = getResourceAsStream(resourcePath);

		byte[] data = null;

		try {
			if (inputStream == null) {

				// It may be a generated inner class

				data = _urlWeavingAdaptor.removeGeneratedClassDate(name);
			}
			else {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
					new UnsyncByteArrayOutputStream();

				StreamUtil.transfer(
					inputStream, unsyncByteArrayOutputStream, true);

				data = unsyncByteArrayOutputStream.toByteArray();
			}

			if (data == null) {
				throw new ClassNotFoundException(name);
			}

			byte[] oldData = data;

			try {
				data = _urlWeavingAdaptor.weaveClass(name, data, false);
			}
			catch (AbortException ae) {
				if (_log.isWarnEnabled()) {
					_log.warn("Abort weaving class " + name, ae);
				}
			}

			if (Arrays.equals(oldData, data)) {
				return _generateClass(name, data);
			}

			if (_dumpDir != null) {
				File dumpFile = new File(_dumpDir, resourcePath);

				File dumpDir = dumpFile.getParentFile();

				dumpDir.mkdirs();

				FileOutputStream fileOutputStream = new FileOutputStream(
					dumpFile);

				fileOutputStream.write(data);

				fileOutputStream.close();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Woven class " + name + " result in " +
							dumpFile.getCanonicalPath());
				}
			}
			else {
				if (_log.isInfoEnabled()) {
					_log.info("Woven class " + name);
				}
			}

			return _generateClass(name, data);
		}
		catch (IOException ioe) {
			throw new ClassNotFoundException(name, ioe);
		}
	}

	private Class<?> _generateClass(String name, byte[] data) {
		Class<?> clazz = defineClass(
			name, data, 0, data.length, (ProtectionDomain)null);

		String packageName = null;

		int index = name.lastIndexOf('.');

		if (index != -1) {
			packageName = name.substring(0, index);
		}

		if (packageName != null) {
			Package pkg = getPackage(packageName);

			if (pkg == null) {
				definePackage(
					packageName, null, null, null, null, null, null, null);
			}
		}

		return clazz;
	}

	private static Log _log = LogFactoryUtil.getLog(WeavingClassLoader.class);

	private File _dumpDir;
	private URLWeavingAdaptor _urlWeavingAdaptor;

}