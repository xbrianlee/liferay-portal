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

import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portlet.documentlibrary.model.DLProcessorConstants;

import java.io.InputStream;

/**
 * @author Sergio González
 */
public class PDFProcessorUtil {

	public static void generateImages(
			FileVersion sourceFileVersion, FileVersion destinationFileVersion)
		throws Exception {

		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor != null) {
			pdfProcessor.generateImages(
				sourceFileVersion, destinationFileVersion);
		}
	}

	public static PDFProcessor getPDFProcessor() {
		return (PDFProcessor)DLProcessorRegistryUtil.getDLProcessor(
			DLProcessorConstants.PDF_PROCESSOR);
	}

	public static InputStream getPreviewAsStream(
			FileVersion fileVersion, int index)
		throws Exception {

		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return null;
		}

		return pdfProcessor.getPreviewAsStream(fileVersion, index);
	}

	public static int getPreviewFileCount(FileVersion fileVersion) {
		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return 0;
		}

		return pdfProcessor.getPreviewFileCount(fileVersion);
	}

	public static long getPreviewFileSize(FileVersion fileVersion, int index)
		throws Exception {

		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return 0;
		}

		return pdfProcessor.getPreviewFileSize(fileVersion, index);
	}

	public static InputStream getThumbnailAsStream(
			FileVersion fileVersion, int index)
		throws Exception {

		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return null;
		}

		return pdfProcessor.getThumbnailAsStream(fileVersion, index);
	}

	public static long getThumbnailFileSize(FileVersion fileVersion, int index)
		throws Exception {

		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return 0;
		}

		return pdfProcessor.getThumbnailFileSize(fileVersion, index);
	}

	public static boolean hasImages(FileVersion fileVersion) {
		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return false;
		}

		return pdfProcessor.hasImages(fileVersion);
	}

	public static boolean isDocumentSupported(FileVersion fileVersion) {
		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return false;
		}

		return pdfProcessor.isDocumentSupported(fileVersion);
	}

	public static boolean isDocumentSupported(String mimeType) {
		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return false;
		}

		return pdfProcessor.isDocumentSupported(mimeType);
	}

	public static boolean isSupported(String mimeType) {
		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor == null) {
			return false;
		}

		return pdfProcessor.isSupported(mimeType);
	}

	public static void trigger(
		FileVersion sourceFileVersion, FileVersion destinationFileVersion) {

		PDFProcessor pdfProcessor = getPDFProcessor();

		if (pdfProcessor != null) {
			pdfProcessor.trigger(sourceFileVersion, destinationFileVersion);
		}
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPDFProcessor(PDFProcessor pdfProcessor) {
	}

}