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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Pastor
 */
@PowerMockIgnore("javax.xml.datatype.*")
@PrepareForTest({PortalUtil.class})
@RunWith(PowerMockRunner.class)
public class HttpImplTest extends PowerMockito {

	@Test
	public void testAddBooleanParameter() {
		_addParameter("http://foo.com", "p", String.valueOf(Boolean.TRUE));
	}

	@Test
	public void testAddDoubleParameter() {
		_addParameter("http://foo.com", "p", String.valueOf(111.1D));
	}

	@Test
	public void testAddIntParameter() {
		_addParameter("http://foo.com", "p", String.valueOf(1));
	}

	@Test
	public void testAddLongParameter() {
		_addParameter("http://foo.com", "p", String.valueOf(111111L));
	}

	@Test
	public void testAddShortParameter() {
		_addParameter("http://foo.com", "p", String.valueOf((short)1));
	}

	@Test
	public void testAddStringParameter() {
		_addParameter("http://foo.com", "p", new String("foo"));
	}

	@Test
	public void testDecodeMultipleCharacterEncodedPath() {
		Assert.assertEquals(
			"http://foo?p=$param",
			_httpImpl.decodePath("http://foo%3Fp%3D%24param"));
	}

	@Test
	public void testDecodeNoCharacterEncodedPath() {
		Assert.assertEquals("http://foo", _httpImpl.decodePath("http://foo"));
	}

	@Test
	public void testDecodeSingleCharacterEncodedPath() {
		Assert.assertEquals(
			"http://foo#anchor", _httpImpl.decodePath("http://foo%23anchor"));
	}

	@Test
	public void testEncodeMultipleCharacterEncodedPath() {
		Assert.assertEquals(
			"http%3A//foo%3Fp%3D%24param",
			_httpImpl.encodePath("http://foo?p=$param"));
	}

	@Test
	public void testEncodeNoCharacterEncodedPath() {
		Assert.assertEquals("http%3A//foo", _httpImpl.encodePath("http://foo"));
	}

	@Test
	public void testEncodeSingleCharacterEncodedPath() {
		Assert.assertEquals(
			"http%3A//foo%23anchor", _httpImpl.encodePath("http://foo#anchor"));
	}

	@Test
	public void testGetParameterMapWithCorrectQuery() {
		Map<String, String[]> parameterMap = _httpImpl.getParameterMap(
			"a=1&b=2");

		Assert.assertNotNull(parameterMap);

		Assert.assertEquals("1", parameterMap.get("a")[0]);
		Assert.assertEquals("2", parameterMap.get("b")[0]);
	}

	@Test
	public void testGetParameterMapWithMultipleBadParameter() {
		Map<String, String[]> parameterMap = _httpImpl.getParameterMap(
			"null&a=1&null");

		Assert.assertNotNull(parameterMap);

		Assert.assertEquals("1", parameterMap.get("a")[0]);
	}

	@Test
	public void testGetParameterMapWithSingleBadParameter() {
		Map<String, String[]> parameterMap = _httpImpl.getParameterMap(
			"null&a=1");

		Assert.assertNotNull(parameterMap);

		Assert.assertEquals("1", parameterMap.get("a")[0]);
	}

	@Test
	public void testProtocolizeMalformedURL() {
		Assert.assertEquals(
			"foo.com", _httpImpl.protocolize("foo.com", 8080, true));
	}

	@Test
	public void testProtocolizeNonsecure() {
		Assert.assertEquals(
			"http://foo.com:8080",
			_httpImpl.protocolize("https://foo.com", 8080, false));
	}

	@Test
	public void testProtocolizeSecure() {
		Assert.assertEquals(
			"https://foo.com:8443",
			_httpImpl.protocolize("http://foo.com", 8443, true));
	}

	@Test
	public void testProtocolizeWithoutPort() {
		Assert.assertEquals(
			"http://foo.com/web/guest",
			_httpImpl.protocolize("https://foo.com:8443/web/guest", -1, false));
	}

	private void _addParameter(
		String url, String parameterName, String parameterValue) {

		mockStatic(PortalUtil.class);

		when(
			PortalUtil.stripURLAnchor(url, StringPool.POUND)
		).thenReturn(
			new String[] {url, StringPool.BLANK}
		);

		String newURL = _httpImpl.addParameter(
			url, parameterName, parameterValue);

		verifyStatic();

		StringBundler sb = new StringBundler(5);

		sb.append(url);
		sb.append(StringPool.QUESTION);
		sb.append(parameterName);
		sb.append(StringPool.EQUAL);
		sb.append(parameterValue);

		Assert.assertEquals(sb.toString(), newURL);
	}

	private HttpImpl _httpImpl = new HttpImpl();

}