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

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Tuple;

import java.util.List;

/**
 * @author Julio Camarero
 */
public class LayoutPrototypeException extends PortalException {

	public LayoutPrototypeException() {
		super();
	}

	public LayoutPrototypeException(List<Tuple> missingLayoutPrototypes) {
		super();

		_missingLayoutPrototypes = missingLayoutPrototypes;
	}

	public LayoutPrototypeException(String msg) {
		super(msg);
	}

	public LayoutPrototypeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public LayoutPrototypeException(Throwable cause) {
		super(cause);
	}

	public List<Tuple> getMissingLayoutPrototypes() {
		return _missingLayoutPrototypes;
	}

	public void setMissingLayoutPrototypes(
		List<Tuple> missingLayoutPrototypes) {

		_missingLayoutPrototypes = missingLayoutPrototypes;
	}

	private List<Tuple> _missingLayoutPrototypes;

}