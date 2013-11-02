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

package com.liferay.util.freemarker;

import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.memory.FinalizeAction;
import com.liferay.portal.kernel.memory.FinalizeManager;
import com.liferay.portal.kernel.util.ContextPathUtil;
import com.liferay.portal.kernel.util.StringPool;

import freemarker.ext.jsp.TaglibFactory;

import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

/**
 * @author Shuyang Zhou
 */
public class FreeMarkerTaglibFactoryUtil implements CacheRegistryItem {

	public static TemplateHashModel createTaglibFactory(
		ServletContext servletContext) {

		return new TaglibFactoryCacheWrapper(servletContext);
	}

	@Override
	public String getRegistryName() {
		return _registryName;
	}

	@Override
	public void invalidate() {
		_templateModels.clear();
	}

	private static FreeMarkerTaglibFactoryUtil _getInstance(
		ServletContext servletContext) {

		if (_instance != null) {
			return _instance;
		}

		synchronized(FreeMarkerTaglibFactoryUtil.class) {
			if (_instance == null) {
				String contextPath = ContextPathUtil.getContextPath(
					servletContext);

				// First call within current class loader

				_instance = new FreeMarkerTaglibFactoryUtil(contextPath);

				// Unregister previous one if there is one, this should only
				// happen on plugin redeploy

				CacheRegistryUtil.unregister(_instance._registryName);

				// Register current instance

				CacheRegistryUtil.register(_instance);

				// Save a hard stack copy to prevent Tomcat null out heap
				// reference

				final String name = _instance._registryName;

				// Bind _instance lifecycle to servlet context to prevent memory
				// leak on undeploy

				FinalizeManager.register(
					servletContext,
					new FinalizeAction() {

						@Override
						public void doFinalize() {
							CacheRegistryUtil.unregister(name);
						}

					});
			}
		}

		return _instance;
	}

	private FreeMarkerTaglibFactoryUtil(String contextPath) {
		_contextPath = contextPath;
		_registryName = FreeMarkerTaglibFactoryUtil.class.getName().concat(
			StringPool.AT).concat(_contextPath);
	}

	private static volatile FreeMarkerTaglibFactoryUtil _instance;

	private final String _contextPath;
	private final String _registryName;
	private Map<String, TemplateModel> _templateModels =
		new ConcurrentHashMap<String, TemplateModel>();

	private static class TaglibFactoryCacheWrapper
		implements TemplateHashModel {

		public TaglibFactoryCacheWrapper(ServletContext servletContext) {
			FreeMarkerTaglibFactoryUtil freeMarkerTaglibFactoryUtil =
				_getInstance(servletContext);

			_templateModels = freeMarkerTaglibFactoryUtil._templateModels;
			_taglibFactory = new TaglibFactory(servletContext);
		}

		@Override
		public TemplateModel get(String uri) throws TemplateModelException {
			TemplateModel templateModel = _templateModels.get(uri);

			if (templateModel == null) {
				templateModel = _taglibFactory.get(uri);

				_templateModels.put(uri, templateModel);
			}

			return templateModel;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		private TaglibFactory _taglibFactory;
		private Map<String, TemplateModel> _templateModels;

	}

}