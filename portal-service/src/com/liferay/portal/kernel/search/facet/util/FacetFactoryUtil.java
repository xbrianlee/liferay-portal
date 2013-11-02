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

package com.liferay.portal.kernel.search.facet.util;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;

import java.lang.reflect.Constructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Raymond Augé
 */
public class FacetFactoryUtil {

	public static Facet create(
			SearchContext searchContext, FacetConfiguration facetConfiguration)
		throws Exception {

		String className = facetConfiguration.getClassName();

		Constructor<?> constructor = _constructorCache.get(className);

		if (constructor == null) {
			constructor = Class.forName(className).getConstructor(
				SearchContext.class);

			_constructorCache.put(className, constructor);
		}

		Facet facet = (Facet)constructor.newInstance(searchContext);

		facet.setFacetConfiguration(facetConfiguration);

		return facet;
	}

	private static Map<String, Constructor<?>> _constructorCache =
		new ConcurrentHashMap<String, Constructor<?>>();

}