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

package com.liferay.portal.freemarker;

import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

import freemarker.core.Environment;
import freemarker.core.TemplateClassResolver;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.ObjectConstructor;

/**
 * @author Raymond Augé
 */
public class LiferayTemplateClassResolver implements TemplateClassResolver {

	@Override
	public Class<?> resolve(
			String className, Environment environment, Template template)
		throws TemplateException {

		if (className.equals(ObjectConstructor.class.getName())) {
			throw new TemplateException(
				"Instantiating " + className + " is not allowed in the " +
					"template for security reasons",
				environment);
		}

		for (String restrictedClassName :
				PropsValues.FREEMARKER_ENGINE_RESTRICTED_CLASSES) {

			if (className.equals(restrictedClassName)) {
				throw new TemplateException(
					"Instantiating " + className + " is not allowed in the " +
						"template for security reasons",
					environment);
			}
		}

		for (String restrictedPackageName :
				PropsValues.FREEMARKER_ENGINE_RESTRICTED_PACKAGES) {

			if (className.startsWith(restrictedPackageName)) {
				throw new TemplateException(
					"Instantiating " + className + " is not allowed in the " +
						"template for security reasons",
					environment);
			}
		}

		try {
			return Class.forName(
				className, true, ClassLoaderUtil.getContextClassLoader());
		}
		catch (Exception e) {
			throw new TemplateException(e, environment);
		}
	}

}