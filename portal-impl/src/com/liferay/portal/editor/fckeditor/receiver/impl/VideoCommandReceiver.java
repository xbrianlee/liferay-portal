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
import com.liferay.portlet.documentlibrary.util.VideoProcessorUtil;

import java.util.Set;

/**
 * @author Juan Gonzalez
 * @author Roberto Díaz
 */
public class VideoCommandReceiver extends BaseFileEntryCommandReceiver {

	@Override
	protected String[] getFileEntryMimeTypes() {
		Set<String> videoMimeTypes = VideoProcessorUtil.getVideoMimeTypes();

		if (videoMimeTypes == null) {
			return null;
		}

		return ArrayUtil.toStringArray(videoMimeTypes.toArray());
	}

	@Override
	protected String getUnavaiablePreviewErrorMessage() {
		return _UNAVAIABLE_PREVIEW_ERROR_MESSAGE;
	}

	@Override
	protected int getXugglerDisabledFileUploadReturnValue() {
		return ServletResponseConstants.SC_VIDEO_PREVIEW_DISABLED_EXCEPTION;
	}

	@Override
	protected boolean hasFileEntryPreview(FileVersion fileVersion) {
		return VideoProcessorUtil.hasVideo(fileVersion);
	}

	private static final String _UNAVAIABLE_PREVIEW_ERROR_MESSAGE =
		"the-video-preview-is-not-yet-ready.-please-try-again-later";

}