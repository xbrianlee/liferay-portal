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

package com.liferay.portlet.internal;

import com.liferay.portal.kernel.bean.BeanLocatorException;
import com.liferay.portal.model.impl.PortletImpl;
import com.liferay.portlet.PortletBagFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.mock.web.MockServletContext;

/**
 * @author Raymond Augé
 */
public class PortletBagFactoryTest extends TestCase {

	@Test
	public void test1() throws Exception {
		try {
			PortletBagFactory portletBagFactory = new PortletBagFactory();

			portletBagFactory.create(new PortletImpl());

			Assert.fail();
		}
		catch (IllegalStateException ise) {
		}
	}

	@Test
	public void test2() throws Exception {
		try {
			PortletBagFactory portletBagFactory = new PortletBagFactory();

			portletBagFactory.setClassLoader(getClass().getClassLoader());

			portletBagFactory.create(new PortletImpl());

			Assert.fail();
		}
		catch (IllegalStateException ise) {
		}
	}

	@Test
	public void test3() throws Exception {
		try {
			PortletBagFactory portletBagFactory = new PortletBagFactory();

			portletBagFactory.setClassLoader(getClass().getClassLoader());
			portletBagFactory.setServletContext(new MockServletContext());

			portletBagFactory.create(new PortletImpl());

			Assert.fail();
		}
		catch (IllegalStateException ise) {
		}
	}

	@Test
	public void test4_initializedInstance() throws Exception {
		try {
			PortletImpl portletImpl = new PortletImpl();

			portletImpl.setPortletClass(MVCPortlet.class.getName());

			PortletBagFactory portletBagFactory = new PortletBagFactory();

			portletBagFactory.setClassLoader(getClass().getClassLoader());
			portletBagFactory.setServletContext(new MockServletContext());
			portletBagFactory.setWARFile(false);

			portletBagFactory.create(portletImpl);

			Assert.fail();
		}
		catch (BeanLocatorException ble) {
		}
		catch (NullPointerException npe) {
		}
	}

	@Test
	public void test5_concreteInstance() throws Exception {
		try {
			PortletImpl portletImpl = new PortletImpl();

			final MVCPortlet mvcPortlet = new MVCPortlet();

			PortletBagFactory portletBagFactory = new PortletBagFactory() {

				@Override
				protected Portlet getPortletInstance(
					com.liferay.portal.model.Portlet portlet) {

					return mvcPortlet;
				}

			};

			portletBagFactory.setClassLoader(getClass().getClassLoader());
			portletBagFactory.setServletContext(new MockServletContext());
			portletBagFactory.setWARFile(false);

			portletBagFactory.create(portletImpl);

			Assert.fail();
		}
		catch (BeanLocatorException ble) {
		}
		catch (NullPointerException npe) {
		}
	}

}