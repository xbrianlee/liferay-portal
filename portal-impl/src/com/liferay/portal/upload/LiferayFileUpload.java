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

package com.liferay.portal.upload;

import com.liferay.portal.kernel.util.ProgressTracker;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Brian Myunghun Kim
 * @author Brian Wing Shun Chan
 */
public class LiferayFileUpload extends ServletFileUpload {

	public static final String FILE_NAME =
		LiferayFileUpload.class.getName() + "_FILE_NAME";

	public static final String PERCENT = ProgressTracker.PERCENT;

	public LiferayFileUpload(
		FileItemFactory fileItemFactory, HttpServletRequest request) {

		super(fileItemFactory);

		_session = request.getSession();
	}

	@Override
	public List<LiferayFileItem> parseRequest(HttpServletRequest request)
		throws FileUploadException {

		_session.removeAttribute(LiferayFileUpload.FILE_NAME);
		_session.removeAttribute(LiferayFileUpload.PERCENT);

		return super.parseRequest(request);
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@Override
	@SuppressWarnings("rawtypes")
	protected FileItem createItem(Map headers, boolean formField)
		throws FileUploadException {

		LiferayFileItem item = (LiferayFileItem)super.createItem(
			headers, formField);

		String fileName = item.getFileName();

		if (Validator.isNotNull(fileName)) {
			_session.setAttribute(LiferayFileUpload.FILE_NAME, fileName);
		}

		return item;
	}

	private HttpSession _session;

}