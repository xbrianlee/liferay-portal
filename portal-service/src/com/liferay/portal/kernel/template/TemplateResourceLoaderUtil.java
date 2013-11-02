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

package com.liferay.portal.kernel.template;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tina Tian
 */
public class TemplateResourceLoaderUtil {

	public static void clearCache() {
		for (TemplateResourceLoader templateResourceLoader :
				_templateResourceLoaders.values()) {

			templateResourceLoader.clearCache();
		}
	}

	public static void clearCache(String templateResourceLoaderName)
		throws TemplateException {

		TemplateResourceLoader templateResourceLoader =
			_getTemplateResourceLoader(templateResourceLoaderName);

		templateResourceLoader.clearCache();
	}

	public static void clearCache(
			String templateResourceLoaderName, String templateId)
		throws TemplateException {

		TemplateResourceLoader templateResourceLoader =
			_getTemplateResourceLoader(templateResourceLoaderName);

		templateResourceLoader.clearCache(templateId);
	}

	public static void destroy() {
		for (TemplateResourceLoader templateResourceLoader :
				_templateResourceLoaders.values()) {

			templateResourceLoader.destroy();
		}

		_templateResourceLoaders.clear();
	}

	public static TemplateResource getTemplateResource(
			String templateResourceLoaderName, String templateId)
		throws TemplateException {

		TemplateResourceLoader templateResourceLoader =
			_getTemplateResourceLoader(templateResourceLoaderName);

		return templateResourceLoader.getTemplateResource(templateId);
	}

	public static TemplateResourceLoader getTemplateResourceLoader(
		String templateResourceLoaderName) {

		return _templateResourceLoaders.get(templateResourceLoaderName);
	}

	public static Set<String> getTemplateResourceLoaderNames() {
		return _templateResourceLoaders.keySet();
	}

	public static Map<String, TemplateResourceLoader>
		getTemplateResourceLoaders() {

		return Collections.unmodifiableMap(_templateResourceLoaders);
	}

	public static boolean hasTemplateResource(
			String templateResourceLoaderName, String templateId)
		throws TemplateException {

		TemplateResourceLoader templateResourceLoader =
			_getTemplateResourceLoader(templateResourceLoaderName);

		return templateResourceLoader.hasTemplateResource(templateId);
	}

	public static boolean hasTemplateResourceLoader(
		String templateResourceLoaderName) {

		return _templateResourceLoaders.containsKey(templateResourceLoaderName);
	}

	public static void registerTemplateResourceLoader(
		TemplateResourceLoader templateResourceLoader) {

		_templateResourceLoaders.put(
			templateResourceLoader.getName(), templateResourceLoader);
	}

	public static void unregisterTemplateResourceLoader(
		String templateResourceLoaderName) {

		TemplateResourceLoader templateResourceLoader =
			_templateResourceLoaders.remove(templateResourceLoaderName);

		if (templateResourceLoader != null) {
			templateResourceLoader.destroy();
		}
	}

	public void setTemplateResourceLoaders(
		List<TemplateResourceLoader> templateResourceLoaders) {

		for (TemplateResourceLoader templateResourceLoader :
				templateResourceLoaders) {

			_templateResourceLoaders.put(
				templateResourceLoader.getName(), templateResourceLoader);
		}
	}

	private static TemplateResourceLoader _getTemplateResourceLoader(
			String templateResourceLoaderName)
		throws TemplateException {

		TemplateResourceLoader templateResourceLoader =
			_templateResourceLoaders.get(templateResourceLoaderName);

		if (templateResourceLoader == null) {
			throw new TemplateException(
				"Unsupported template resource loader " +
					templateResourceLoaderName);
		}

		return templateResourceLoader;
	}

	private static Map<String, TemplateResourceLoader>
		_templateResourceLoaders =
			new ConcurrentHashMap<String, TemplateResourceLoader>();

}