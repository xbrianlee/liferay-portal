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

package com.liferay.portal.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class SpriteImage implements Serializable {

	public SpriteImage(
		String spriteFileName, String imageFileName, int offset, int height,
		int width) {

		_spriteFileName = spriteFileName;
		_imageFileName = imageFileName;
		_offset = offset;
		_height = height;
		_width = width;
	}

	public int getHeight() {
		return _height;
	}

	public String getImageFileName() {
		return _imageFileName;
	}

	public int getOffset() {
		return _offset;
	}

	public String getSpriteFileName() {
		return _spriteFileName;
	}

	public int getWidth() {
		return _width;
	}

	private int _height;
	private String _imageFileName;
	private int _offset;
	private String _spriteFileName;
	private int _width;

}