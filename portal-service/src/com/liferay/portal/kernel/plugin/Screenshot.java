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

package com.liferay.portal.kernel.plugin;

/**
 * @author Jorge Ferrer
 */
public class Screenshot {

	public String getLargeImageURL() {
		return _largeImageURL;
	}

	public String getThumbnailURL() {
		return _thumbnailURL;
	}

	public void setLargeImageURL(String largeImageURL) {
		_largeImageURL = largeImageURL;
	}

	public void setThumbnailURL(String thumbnailURL) {
		_thumbnailURL = thumbnailURL;
	}

	private String _largeImageURL;
	private String _thumbnailURL;

}