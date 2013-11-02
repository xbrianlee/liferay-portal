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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author David Mendez Gonzalez
 */
public class TokenizerUtil {

	public static Tokenizer getTokenizer() {
		PortalRuntimePermission.checkGetBeanProperty(TokenizerUtil.class);

		return _tokenizer;
	}

	public static List<String> tokenize(
			String fieldName, String input, String languageId)
		throws SearchException {

		return getTokenizer().tokenize(fieldName, input, languageId);
	}

	public void setTokenizer(Tokenizer tokenizer) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_tokenizer = tokenizer;
	}

	private static Tokenizer _tokenizer;

}