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

import com.liferay.portal.model.Image;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.concurrent.Future;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public interface ImageTool {

	public static final String TYPE_BMP = "bmp";

	public static final String TYPE_GIF = "gif";

	public static final String TYPE_JPEG = "jpg";

	public static final String TYPE_NOT_AVAILABLE = "na";

	public static final String TYPE_PNG = "png";

	public static final String TYPE_TIFF = "tiff";

	public Future<RenderedImage> convertCMYKtoRGB(byte[] bytes, String type);

	public BufferedImage convertImageType(BufferedImage sourceImage, int type);

	public void encodeGIF(RenderedImage renderedImage, OutputStream os)
		throws IOException;

	public void encodeWBMP(RenderedImage renderedImage, OutputStream os)
		throws IOException;

	public BufferedImage getBufferedImage(RenderedImage renderedImage);

	public byte[] getBytes(RenderedImage renderedImage, String contentType)
		throws IOException;

	public Image getDefaultCompanyLogo();

	public Image getDefaultOrganizationLogo();

	public Image getDefaultSpacer();

	public Image getDefaultUserFemalePortrait();

	public Image getDefaultUserMalePortrait();

	public Image getImage(byte[] bytes)
		throws IOException;

	public Image getImage(File file) throws IOException;

	public Image getImage(InputStream is) throws IOException;

	public Image getImage(InputStream is, boolean cleanUpStream)
		throws IOException;

	public boolean isNullOrDefaultSpacer(byte[] bytes);

	public ImageBag read(byte[] bytes) throws IOException;

	public ImageBag read(File file) throws IOException;

	public ImageBag read(InputStream inputStream) throws IOException;

	public RenderedImage scale(RenderedImage renderedImage, int width);

	public RenderedImage scale(
		RenderedImage renderedImage, int maxHeight, int maxWidth);

	public abstract void write(
			RenderedImage renderedImage, String contentType, OutputStream os)
		throws IOException;

}