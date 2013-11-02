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

package com.liferay.portal.kernel.template.comparator;

import com.liferay.portal.kernel.template.TemplateHandler;

import java.io.Serializable;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Eduardo Garcia
 */
public class TemplateHandlerComparator
	implements Comparator<TemplateHandler>, Serializable {

	public TemplateHandlerComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(
		TemplateHandler templateHandler1, TemplateHandler templateHandler2) {

		String templateHandlerName1 = templateHandler1.getName(_locale);
		String templateHandlerName2 = templateHandler2.getName(_locale);

		return templateHandlerName1.compareTo(templateHandlerName2);
	}

	private Locale _locale;

}