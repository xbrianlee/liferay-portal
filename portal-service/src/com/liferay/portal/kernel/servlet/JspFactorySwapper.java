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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.ServerDetector;

import javax.servlet.jsp.JspFactory;

/**
 * @author Shuyang Zhou
 */
public class JspFactorySwapper {

	public static void swap() {
		if (!ServerDetector.isTomcat()) {
			return;
		}

		JspFactory jspFactory = JspFactory.getDefaultFactory();

		if (jspFactory instanceof JspFactoryWrapper) {
			return;
		}

		synchronized (JspFactorySwapper.class) {
			if (_jspFactoryWrapper == null) {
				_jspFactoryWrapper = new JspFactoryWrapper(jspFactory);
			}

			JspFactory.setDefaultFactory(_jspFactoryWrapper);
		}
	}

	private static JspFactoryWrapper _jspFactoryWrapper;

}