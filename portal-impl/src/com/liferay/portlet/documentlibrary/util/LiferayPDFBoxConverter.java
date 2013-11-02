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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.image.ImageToolImpl;
import com.liferay.portal.kernel.image.ImageTool;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;

import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 * @author Juan Gonzalez
 */
public class LiferayPDFBoxConverter {

	public LiferayPDFBoxConverter(
		File inputFile, File thumbnailFile, File[] previewFiles,
		String extension, String thumbnailExtension, int dpi, int height,
		int width, boolean generatePreview, boolean generateThumbnail) {

		_inputFile = inputFile;
		_thumbnailFile = thumbnailFile;
		_previewFiles = previewFiles;
		_extension = extension;
		_thumbnailExtension = thumbnailExtension;
		_dpi = dpi;
		_height = height;
		_width = width;
		_generatePreview = generatePreview;
		_generateThumbnail = generateThumbnail;
	}

	public void generateImagesPB() throws Exception {
		PDDocument pdDocument = null;

		try {
			pdDocument = PDDocument.load(_inputFile);

			PDDocumentCatalog pdDocumentCatalog =
				pdDocument.getDocumentCatalog();

			List<PDPage> pdPages = pdDocumentCatalog.getAllPages();

			for (int i = 0; i < pdPages.size(); i++) {
				PDPage pdPage = pdPages.get(i);

				if (_generateThumbnail && (i == 0)) {
					_generateImagesPB(
						pdPage, i, _thumbnailFile, _thumbnailExtension);
				}

				if (!_generatePreview) {
					break;
				}

				_generateImagesPB(pdPage, i + 1, _previewFiles[i], _extension);
			}
		}
		finally {
			if (pdDocument != null) {
				pdDocument.close();
			}
		}
	}

	private void _generateImagesPB(
			PDPage pdPage, int index, File outputFile, String extension)
		throws Exception {

		RenderedImage renderedImage = pdPage.convertToImage(
			BufferedImage.TYPE_INT_RGB, _dpi);

		ImageTool imageTool = ImageToolImpl.getInstance();

		if (_height != 0) {
			renderedImage = imageTool.scale(renderedImage, _width, _height);
		}
		else {
			renderedImage = imageTool.scale(renderedImage, _width);
		}

		outputFile.createNewFile();

		ImageIO.write(renderedImage, extension, outputFile);
	}

	private int _dpi;
	private String _extension;
	private boolean _generatePreview;
	private boolean _generateThumbnail;
	private int _height;
	private File _inputFile;
	private File[] _previewFiles;
	private String _thumbnailExtension;
	private File _thumbnailFile;
	private int _width;

}