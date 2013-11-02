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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.StagedModel;

/**
 * @author Raymond Augé
 */
public class PortletDataException extends PortalException {

	public static final int DEFAULT = 1;

	public static final int END_DATE_IS_MISSING_START_DATE = 1;

	public static final int FUTURE_END_DATE = 2;

	public static final int FUTURE_START_DATE = 3;

	public static final int INVALID_GROUP = 4;

	public static final int MISSING_DEPENDENCY = 5;

	public static final int START_DATE_AFTER_END_DATE = 6;

	public static final int START_DATE_IS_MISSING_END_DATE = 7;

	public static final int STATUS_IN_TRASH = 8;

	public static final int STATUS_UNAVAILABLE = 9;

	public PortletDataException() {
		super();
	}

	public PortletDataException(int type) {
		_type = type;
	}

	public PortletDataException(String msg) {
		super(msg);
	}

	public PortletDataException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PortletDataException(Throwable cause) {
		super(cause);
	}

	public StagedModel getStagedModel() {
		return _stagedModel;
	}

	public int getType() {
		return _type;
	}

	public void setStagedModel(StagedModel stagedModel) {
		_stagedModel = stagedModel;
	}

	public void setType(int type) {
		_type = type;
	}

	private StagedModel _stagedModel;
	private int _type = DEFAULT;

}