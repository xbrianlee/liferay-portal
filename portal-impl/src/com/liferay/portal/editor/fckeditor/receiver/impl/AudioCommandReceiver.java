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

package com.liferay.portal.editor.fckeditor.receiver.impl;

import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portlet.documentlibrary.util.AudioProcessorUtil;

import java.util.Set;

/**
 * @author Juan Gonzalez
 */
public class AudioCommandReceiver extends BaseFileEntryCommandReceiver {

	@Override
	protected String[] getFileEntryMimeTypes() {
		Set<String> audioMimeTypes = AudioProcessorUtil.getAudioMimeTypes();

		if (audioMimeTypes == null) {
			return null;
		}

		return ArrayUtil.toStringArray(audioMimeTypes.toArray());
	}

	@Override
	protected String getUnavaiablePreviewErrorMessage() {
		return _UNAVAIABLE_PREVIEW_ERROR_MESSAGE;
	}

	@Override
	protected int getXugglerDisabledFileUploadReturnValue() {
		return ServletResponseConstants.SC_AUDIO_PREVIEW_DISABLED_EXCEPTION;
	}

	@Override
	protected boolean hasFileEntryPreview(FileVersion fileVersion) {
		return AudioProcessorUtil.hasAudio(fileVersion);
	}

	private static final String _UNAVAIABLE_PREVIEW_ERROR_MESSAGE =
		"the-audio-preview-is-not-yet-ready.-please-try-again-later";

}