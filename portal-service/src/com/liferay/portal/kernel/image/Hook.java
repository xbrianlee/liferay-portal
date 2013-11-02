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

package com.liferay.portal.kernel.image;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Image;

import java.io.InputStream;

/**
 * @author Jorge Ferrer
 */
public interface Hook {

	public void deleteImage(Image image)
		throws PortalException, SystemException;

	public byte[] getImageAsBytes(Image image)
		throws PortalException, SystemException;

	public InputStream getImageAsStream(Image image)
		throws PortalException, SystemException;

	public void updateImage(Image image, String type, byte[] bytes)
		throws PortalException, SystemException;

}