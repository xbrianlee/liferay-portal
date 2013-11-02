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

package com.liferay.portal.tools.propertiesdoc;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Jesse Rao
 * @author James Hinkey
 * @author Hugo Huijser
 */
public class PropertyComment {

	public PropertyComment(String comment) {
		_comment = comment;

		String[] lines = comment.split(StringPool.NEW_LINE);

		for (String line : lines) {
			if (line.startsWith(PropertiesDocBuilder.INDENT)) {
				_preformatted = true;

				return;
			}
		}
	}

	public String getComment() {
		return _comment;
	}

	public boolean isPreformatted() {
		return _preformatted;
	}

	private String _comment;
	private boolean _preformatted;

}