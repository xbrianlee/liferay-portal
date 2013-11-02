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

/**
 * @author Michael C. Han
 */
public class NGramHolderBuilderUtil {

	public static NGramHolder buildNGramHolder(String input)
		throws SearchException {

		return getNGramHolderBuilder().buildNGramHolder(input);
	}

	public static NGramHolder buildNGramHolder(String input, int maxNGramLength)
		throws SearchException {

		return getNGramHolderBuilder().buildNGramHolder(input, maxNGramLength);
	}

	public static NGramHolder buildNGramHolder(
			String input, int nGramMinLength, int nGramMaxLength)
		throws SearchException {

		return getNGramHolderBuilder().buildNGramHolder(
			input, nGramMinLength, nGramMaxLength);
	}

	public static NGramHolderBuilder getNGramHolderBuilder() {
		PortalRuntimePermission.checkGetBeanProperty(
			NGramHolderBuilderUtil.class);

		return _nGramHolderBuilder;
	}

	public void setNGramHolderBuilder(NGramHolderBuilder nGramHolderBuilder) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_nGramHolderBuilder = nGramHolderBuilder;
	}

	private static NGramHolderBuilder _nGramHolderBuilder;

}