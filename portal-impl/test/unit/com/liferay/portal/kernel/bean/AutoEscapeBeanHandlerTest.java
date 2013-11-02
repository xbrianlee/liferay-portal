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

package com.liferay.portal.kernel.bean;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.util.HtmlImpl;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class AutoEscapeBeanHandlerTest {

	@Before
	public void setUp() throws Exception {
		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());

		_bean = new BeanImpl(_UNESCAPED_TEXT);
	}

	@Test
	public void testToEscapedBean() {
		Bean escapedBean = _bean.toEscapedBean();

		Assert.assertEquals(_ESCAPED_TEXT, escapedBean.getAttribute());
		Assert.assertEquals(
			_UNESCAPED_TEXT, escapedBean.getUnescapedAttribute());
	}

	private static final String _ESCAPED_TEXT = "Old Mc&#039;Donald";

	private static final String _UNESCAPED_TEXT = "Old Mc'Donald";

	private Bean _bean;

	private interface Bean extends Serializable {

		@AutoEscape
		public String getAttribute();

		public String getUnescapedAttribute();

		public Bean toEscapedBean();

	}

	private class BeanImpl implements Bean {

		public BeanImpl(String attribute) {
			_attribute = attribute;
			_unescapedAttribute = attribute;
		}

		@Override
		public String getAttribute() {
			return _attribute;
		}

		@Override
		public String getUnescapedAttribute() {
			return _unescapedAttribute;
		}

		@Override
		public Bean toEscapedBean() {
			Class<?> clazz = getClass();

			return (Bean)ProxyUtil.newProxyInstance(
				clazz.getClassLoader(), new Class<?>[] {Bean.class},
				new AutoEscapeBeanHandler(this));
		}

		private String _attribute;
		private String _unescapedAttribute;

	}

}