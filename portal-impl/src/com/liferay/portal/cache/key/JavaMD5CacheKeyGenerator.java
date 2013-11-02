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

package com.liferay.portal.cache.key;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.nio.charset.CharsetEncoderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class JavaMD5CacheKeyGenerator extends BaseCacheKeyGenerator {

	public JavaMD5CacheKeyGenerator() throws NoSuchAlgorithmException {
		this(-1);
	}

	public JavaMD5CacheKeyGenerator(int maxLength)
		throws NoSuchAlgorithmException {

		_maxLength = maxLength;
		_messageDigest = MessageDigest.getInstance(_ALGORITHM_MD5);
		_charsetEncoder = CharsetEncoderUtil.getCharsetEncoder(StringPool.UTF8);
	}

	@Override
	public CacheKeyGenerator clone() {
		try {
			return new JavaMD5CacheKeyGenerator(_maxLength);
		}
		catch (NoSuchAlgorithmException nsae) {
			throw new IllegalStateException(nsae.getMessage(), nsae);
		}
	}

	@Override
	public String getCacheKey(String key) {
		if ((_maxLength > -1) && (key.length() < _maxLength)) {
			return key;
		}

		try {
			_messageDigest.update(_charsetEncoder.encode(CharBuffer.wrap(key)));

			byte[] bytes = _messageDigest.digest();

			return encodeCacheKey(bytes);
		}
		catch (Exception e) {
			_log.error(e, e);

			return key;
		}
	}

	@Override
	public String getCacheKey(String[] keys) {
		return getCacheKey(new StringBundler(keys));
	}

	@Override
	public String getCacheKey(StringBundler sb) {
		if ((_maxLength > -1) && (sb.length() < _maxLength)) {
			return sb.toString();
		}

		try {
			for (int i = 0; i < sb.index(); i++) {
				String key = sb.stringAt(i);

				_messageDigest.update(
					_charsetEncoder.encode(CharBuffer.wrap(key)));
			}

			byte[] bytes = _messageDigest.digest();

			return encodeCacheKey(bytes);
		}
		catch (Exception e) {
			_log.error(e, e);

			return sb.toString();
		}
	}

	@Override
	public boolean isCallingGetCacheKeyThreadSafe() {
		return _CALLING_GET_CACHE_KEY_THREAD_SAFE;
	}

	public void setMaxLength(int maxLength) {
		_maxLength = maxLength;
	}

	protected String encodeCacheKey(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			int value = bytes[i] & 0xff;

			_encodeBuffer[i * 2] = _HEX_CHARACTERS[value >> 4];
			_encodeBuffer[i * 2 + 1] = _HEX_CHARACTERS[value & 0xf];
		}

		return new String(_encodeBuffer);
	}

	private static final String _ALGORITHM_MD5 = "MD5";

	private static final boolean _CALLING_GET_CACHE_KEY_THREAD_SAFE = false;

	private static final char[] _HEX_CHARACTERS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'
	};

	private static Log _log = LogFactoryUtil.getLog(
		JavaMD5CacheKeyGenerator.class);

	private CharsetEncoder _charsetEncoder;
	private char[] _encodeBuffer = new char[32];
	private int _maxLength = -1;
	private MessageDigest _messageDigest;

}