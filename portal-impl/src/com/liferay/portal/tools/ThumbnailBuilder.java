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

package com.liferay.portal.tools;

import com.liferay.portal.image.ImageToolImpl;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageTool;
import com.liferay.portal.kernel.util.GetterUtil;

import java.awt.image.RenderedImage;

import java.io.File;

import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Brian Wing Shun Chan
 */
public class ThumbnailBuilder {

	public static void main(String[] args) {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		File originalFile = new File(arguments.get("thumbnail.original.file"));
		File thumbnailFile = new File(
			arguments.get("thumbnail.thumbnail.file"));
		int height = GetterUtil.getInteger(arguments.get("thumbnail.height"));
		int width = GetterUtil.getInteger(arguments.get("thumbnail.width"));
		boolean overwrite = GetterUtil.getBoolean(
			arguments.get("thumbnail.overwrite"));

		new ThumbnailBuilder(
			originalFile, thumbnailFile, height, width, overwrite);
	}

	public ThumbnailBuilder(
		File originalFile, File thumbnailFile, int height, int width,
		boolean overwrite) {

		try {
			if (!originalFile.exists()) {
				return;
			}

			if (!overwrite) {
				if (thumbnailFile.lastModified() >
						originalFile.lastModified()) {

					return;
				}
			}

			ImageBag imageBag = _imageToolUtil.read(originalFile);

			RenderedImage renderedImage = _imageToolUtil.scale(
				imageBag.getRenderedImage(), height, width);

			ImageIO.write(renderedImage, imageBag.getType(), thumbnailFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ImageTool _imageToolUtil = ImageToolImpl.getInstance();

}