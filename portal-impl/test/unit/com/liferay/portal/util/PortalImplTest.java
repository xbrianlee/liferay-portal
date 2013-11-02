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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.mockito.ReturnArgumentCalledAnswer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Pastor
 */
@PowerMockIgnore("javax.xml.datatype.*")
@PrepareForTest({HttpUtil.class})
@RunWith(PowerMockRunner.class)
public class PortalImplTest extends PowerMockito {

	@Test
	public void testUpdateRedirectRemoveLayoutURL() {
		mockStatic(HttpUtil.class);

		when(
			HttpUtil.getQueryString(Mockito.anyString())
		).thenReturn(
			StringPool.BLANK
		);

		when(
			HttpUtil.getParameter(
				Mockito.anyString(), Mockito.anyString(), Mockito.eq(false))
		).thenReturn(
			StringPool.BLANK
		);

		when(
			HttpUtil.encodeURL(Mockito.anyString())
		).thenAnswer(
			new ReturnArgumentCalledAnswer<String>(0)
		);

		when(
			HttpUtil.getPath(Mockito.anyString())
		).thenAnswer(
			new ReturnArgumentCalledAnswer<String>(0)
		);

		Assert.assertEquals(
			"/web/group",
			_portalImpl.updateRedirect(
				"/web/group/layout", "/group/layout", "/group"));

		verifyStatic();
	}

	private PortalImpl _portalImpl = new PortalImpl();

}