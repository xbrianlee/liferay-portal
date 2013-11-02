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

package com.liferay.portal.atom;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;

import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.TargetBuilder;
import org.apache.abdera.protocol.server.TargetType;

/**
 * @author Igor Spasic
 */
public class AtomTargetBuilder implements TargetBuilder {

	@Override
	public String urlFor(
		RequestContext requestContext, Object key, Object param) {

		String url = String.valueOf(requestContext.getBaseUri());

		if (url.endsWith(StringPool.SLASH)) {
			url = url.substring(0, url.length() - 1);
		}

		url += requestContext.getTargetPath();

		String query = StringPool.BLANK;

		int questionIndex = url.indexOf(CharPool.QUESTION);

		if (questionIndex != -1) {
			query = url.substring(questionIndex);

			url = url.substring(0, questionIndex);
		}

		String keyString = key.toString();

		if (keyString.equals(TargetType.SERVICE)) {
			return url + query;
		}

		if (!keyString.equals(TargetType.COLLECTION)) {
			return null;
		}

		String collectionName = CharPool.SLASH + (String)param;

		if (url.endsWith(collectionName)) {
			return url + query;
		}

		if (url.contains(collectionName + CharPool.SLASH)) {
			int collectionIndex = url.indexOf(collectionName);

			collectionIndex += collectionName.length() + 1;

			url = url.substring(0, collectionIndex);

			return url;
		}

		return url + collectionName + query;
	}

}