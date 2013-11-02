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

import freemarker.ext.beans.BeansWrapper;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * @author Raymond Augé
 */
public class LiferayObjectConstructor implements TemplateMethodModelEx {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arguments)
		throws TemplateModelException {

		if (arguments.isEmpty()) {
			throw new TemplateModelException(
				"This method must have at least one argument as the name of " +
					"the class to instantiate");
		}

		Class<?> clazz = null;

		try {
			String className = String.valueOf(arguments.get(0));

			clazz = Class.forName(
				className, true, ClassLoaderUtil.getContextClassLoader());
		}
		catch (Exception e) {
			throw new TemplateModelException(e.getMessage());
		}

		BeansWrapper beansWrapper = BeansWrapper.getDefaultInstance();

		Object object = beansWrapper.newInstance(
			clazz, arguments.subList(1, arguments.size()));

		return beansWrapper.wrap(object);
	}

}