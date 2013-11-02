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

import com.liferay.portal.kernel.templateparser.TemplateNode;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author Mika Koivisto
 */
public class LiferayTemplateModel extends SimpleHash {

	public LiferayTemplateModel(
		TemplateNode templateNode, ObjectWrapper objectWrapper) {

		super(templateNode, objectWrapper);

		_beanModel = new BeanModel(templateNode, (BeansWrapper)objectWrapper);
	}

	@Override
	public TemplateModel get(String key) throws TemplateModelException {
		TemplateModel templateModel = super.get(key);

		if (templateModel != null) {
			return templateModel;
		}

		return _beanModel.get(key);
	}

	private BeanModel _beanModel;

}