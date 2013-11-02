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

package com.liferay.portal.module.framework;

import java.io.IOException;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author Miguel Pastor
 */
public class ModuleFrameworkClassLoader extends URLClassLoader {

	public ModuleFrameworkClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);

		// Some application servers include their own OSGi framework in the
		// bootstrap class loader

		//_systemClassLoader = getSystemClassLoader();
	}

	@Override
	public URL getResource(String name) {
		URL url = null;

		if (_systemClassLoader != null) {
			url = _systemClassLoader.getResource(name);
		}

		if (url == null) {
			url = findResource(name);

			if (url == null) {
				url = super.getResource(name);
			}
		}

		return url;
	}

	@Override
	public Enumeration<URL> getResources(String name) throws IOException {
		final List<URL> urls = new ArrayList<URL>();

		Enumeration<URL> systemURLs = null;

		if (_systemClassLoader != null) {
			systemURLs = _systemClassLoader.getResources(name);
		}

		urls.addAll(_buildURLs(systemURLs));

		Enumeration<URL> localURLs = findResources(name);

		urls.addAll(_buildURLs(localURLs));

		Enumeration<URL> parentURLs = null;

		ClassLoader parentClassLoader = getParent();

		if (parentClassLoader != null) {
			parentURLs = parentClassLoader.getResources(name);
		}

		urls.addAll(_buildURLs(parentURLs));

		return new Enumeration<URL>() {
			final Iterator<URL> iterator = urls.iterator();

			@Override
			public boolean hasMoreElements() {
				return iterator.hasNext();
			}

			@Override
			public URL nextElement() {
				return iterator.next();
			}

		};
	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)
		throws ClassNotFoundException {

		Class<?> clazz = findLoadedClass(name);

		if (clazz == null) {
			if (_systemClassLoader != null) {
				try {
					clazz = _systemClassLoader.loadClass(name);
				}
				catch (ClassNotFoundException cnfe) {
				}
			}

			if (clazz == null) {
				try {
					clazz = findClass(name);
				}
				catch (ClassNotFoundException cnfe) {
					clazz = super.loadClass(name, resolve);
				}
			}
		}

		if (resolve) {
			resolveClass(clazz);
		}

		return clazz;
	}

	private List<URL> _buildURLs(Enumeration<URL> url) {
		if (url == null) {
			return new ArrayList<URL>();
		}

		List<URL> urls = new ArrayList<URL>();

		while (url.hasMoreElements()) {
			urls.add(url.nextElement());
		}

		return urls;
	}

	private ClassLoader _systemClassLoader;

}