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

package com.liferay.portal.kernel.mobile.device;

import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class Dimensions implements Serializable {

	public static final Dimensions UNKNOWN = new Dimensions(-1, -1);

	public Dimensions(float height, float width) {
		_height = height;
		_width = width;
	}

	public float getHeight() {
		return _height;
	}

	public float getWidth() {
		return _width;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{height=");
		sb.append(_height);
		sb.append(", width=");
		sb.append(_width);
		sb.append("}");

		return sb.toString();
	}

	private float _height;
	private float _width;

}