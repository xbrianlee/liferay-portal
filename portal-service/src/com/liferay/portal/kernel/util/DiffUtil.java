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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.Reader;

import java.util.List;

/**
 * This class can compare two different versions of a text. Source refers to the
 * earliest version of the text and target refers to a modified version of
 * source. Changes are considered either as a removal from the source or as an
 * addition to the target. This class detects changes to an entire line and also
 * detects changes within lines, such as, removal or addition of characters.
 * Take a look at <code>DiffTest</code> to see the expected inputs and outputs.
 *
 * @author Bruno Farache
 * @see    com.liferay.portal.kernel.util.DiffUtil
 */
public class DiffUtil {

	/**
	 * This is a diff method with default values.
	 *
	 * @param  source the source text
	 * @param  target the modified version of the source text
	 * @return an array containing two lists of <code>DiffResults</code>, the
	 *         first element contains DiffResults related to changes in source
	 *         and the second element to changes in target
	 */
	public static List<DiffResult>[] diff(Reader source, Reader target) {
		return getDiff().diff(source, target);
	}

	/**
	 * The main entrance of this class. This method will compare the two texts,
	 * highlight the changes by enclosing them with markers and return a list of
	 * <code>DiffResults</code>.
	 *
	 * @param  source the source text
	 * @param  target the modified version of the source text
	 * @param  addedMarkerStart the marker to indicate the start of text added
	 *         to the source
	 * @param  addedMarkerEnd the marker to indicate the end of text added to
	 *         the source
	 * @param  deletedMarkerStart the marker to indicate the start of text
	 *         deleted from the source
	 * @param  deletedMarkerEnd the marker to indicate the end of text deleted
	 *         from the source
	 * @param  margin the vertical margin to use in displaying differences
	 *         between changed line changes
	 * @return an array containing two lists of <code>DiffResults</code>, the
	 *         first element contains DiffResults related to changes in source
	 *         and the second element to changes in target
	 */
	public static List<DiffResult>[] diff(
		Reader source, Reader target, String addedMarkerStart,
		String addedMarkerEnd, String deletedMarkerStart,
		String deletedMarkerEnd, int margin) {

		return getDiff().diff(
			source, target, addedMarkerStart, addedMarkerEnd,
			deletedMarkerStart, deletedMarkerEnd, margin);
	}

	public static Diff getDiff() {
		PortalRuntimePermission.checkGetBeanProperty(DiffUtil.class);

		return _diff;
	}

	public void setDiff(Diff diff) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_diff = diff;
	}

	private static Diff _diff;

}