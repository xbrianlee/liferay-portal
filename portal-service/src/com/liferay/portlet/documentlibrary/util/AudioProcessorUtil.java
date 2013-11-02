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
public class AudioProcessorUtil {

	public static void generateAudio(
			FileVersion sourceFileVersion, FileVersion destinationFileVersion)
		throws Exception {

		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor != null) {
			audioProcessor.generateAudio(
				sourceFileVersion, destinationFileVersion);
		}
	}

	public static Set<String> getAudioMimeTypes() {
		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return null;
		}

		return audioProcessor.getAudioMimeTypes();
	}

	public static AudioProcessor getAudioProcessor() {
		return (AudioProcessor)DLProcessorRegistryUtil.getDLProcessor(
			DLProcessorConstants.AUDIO_PROCESSOR);
	}

	public static InputStream getPreviewAsStream(
			FileVersion fileVersion, String type)
		throws Exception {

		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return null;
		}

		return audioProcessor.getPreviewAsStream(fileVersion, type);
	}

	public static long getPreviewFileSize(FileVersion fileVersion, String type)
		throws Exception {

		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return 0;
		}

		return audioProcessor.getPreviewFileSize(fileVersion, type);
	}

	public static boolean hasAudio(FileVersion fileVersion) {
		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return false;
		}

		return audioProcessor.hasAudio(fileVersion);
	}

	public static boolean isAudioSupported(FileVersion fileVersion) {
		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return false;
		}

		return audioProcessor.isAudioSupported(fileVersion);
	}

	public static boolean isAudioSupported(String mimeType) {
		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return false;
		}

		return audioProcessor.isAudioSupported(mimeType);
	}

	public static boolean isSupported(String mimeType) {
		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return false;
		}

		return audioProcessor.isSupported(mimeType);
	}

	public static void trigger(
		FileVersion sourceFileVersion, FileVersion destinationFileVersion) {

		AudioProcessor audioProcessor = getAudioProcessor();

		if (audioProcessor == null) {
			return;
		}

		audioProcessor.trigger(sourceFileVersion, destinationFileVersion);
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setAudioProcessor(AudioProcessor audioProcessor) {
	}

}