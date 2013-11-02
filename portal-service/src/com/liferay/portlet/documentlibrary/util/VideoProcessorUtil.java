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

import java.util.Set;

/**
 * @author Sergio González
 */
public class VideoProcessorUtil {

	public static void generateVideo(
			FileVersion sourceFileVersion, FileVersion destinationFileVersion)
		throws Exception {

		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor != null) {
			videoProcessor.generateVideo(
				sourceFileVersion, destinationFileVersion);
		}
	}

	public static InputStream getPreviewAsStream(
			FileVersion fileVersion, String type)
		throws Exception {

		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return null;
		}

		return videoProcessor.getPreviewAsStream(fileVersion, type);
	}

	public static long getPreviewFileSize(FileVersion fileVersion, String type)
		throws Exception {

		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return 0;
		}

		return videoProcessor.getPreviewFileSize(fileVersion, type);
	}

	public static InputStream getThumbnailAsStream(
			FileVersion fileVersion, int index)
		throws Exception {

		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return null;
		}

		return videoProcessor.getThumbnailAsStream(fileVersion, index);
	}

	public static long getThumbnailFileSize(FileVersion fileVersion, int index)
		throws Exception {

		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return 0;
		}

		return videoProcessor.getThumbnailFileSize(fileVersion, index);
	}

	public static Set<String> getVideoMimeTypes() {
		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return null;
		}

		return videoProcessor.getVideoMimeTypes();
	}

	public static VideoProcessor getVideoProcessor() {
		return (VideoProcessor)DLProcessorRegistryUtil.getDLProcessor(
			DLProcessorConstants.VIDEO_PROCESSOR);
	}

	public static boolean hasVideo(FileVersion fileVersion) {
		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return false;
		}

		return videoProcessor.hasVideo(fileVersion);
	}

	public static boolean isSupported(String mimeType) {
		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return false;
		}

		return videoProcessor.isSupported(mimeType);
	}

	public static boolean isVideoSupported(FileVersion fileVersion) {
		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return false;
		}

		return videoProcessor.isVideoSupported(fileVersion);
	}

	public static boolean isVideoSupported(String mimeType) {
		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor == null) {
			return false;
		}

		return videoProcessor.isVideoSupported(mimeType);
	}

	public static void trigger(
		FileVersion sourceFileVersion, FileVersion destinationFileVersion) {

		VideoProcessor videoProcessor = getVideoProcessor();

		if (videoProcessor != null) {
			videoProcessor.trigger(sourceFileVersion, destinationFileVersion);
		}
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setVideoProcessor(VideoProcessor videoProcessor) {
	}

}