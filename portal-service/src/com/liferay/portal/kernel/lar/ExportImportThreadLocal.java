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

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Michael C. Han
 */
public class ExportImportThreadLocal {

	public static boolean isExportInProcess() {
		if (isLayoutExportInProcess() || isPortletExportInProcess()) {
			return true;
		}

		return false;
	}

	public static boolean isImportInProcess() {
		if (isLayoutImportInProcess() || isLayoutValidationInProcess() ||
			isPortletImportInProcess() || isPortletValidationInProcess()) {

			return true;
		}

		return false;
	}

	public static boolean isLayoutExportInProcess() {
		return _layoutExportInProcess.get();
	}

	public static boolean isLayoutImportInProcess() {
		return _layoutImportInProcess.get();
	}

	public static boolean isLayoutValidationInProcess() {
		return _layoutValidationInProcess.get();
	}

	public static boolean isPortletExportInProcess() {
		return _portletExportInProcess.get();
	}

	public static boolean isPortletImportInProcess() {
		return _portletImportInProcess.get();
	}

	public static boolean isPortletValidationInProcess() {
		return _portletValidationInProcess.get();
	}

	public static void setLayoutExportInProcess(boolean layoutExportInProcess) {
		_layoutExportInProcess.set(layoutExportInProcess);
	}

	public static void setLayoutImportInProcess(boolean layoutImportInProcess) {
		_layoutImportInProcess.set(layoutImportInProcess);
	}

	public static void setLayoutValidationInProcess(
		boolean layoutValidationInProcess) {

		_layoutValidationInProcess.set(layoutValidationInProcess);
	}

	public static void setPortletExportInProcess(
		boolean portletExportInProcess) {

		_portletExportInProcess.set(portletExportInProcess);
	}

	public static void setPortletImportInProcess(
		boolean portletImportInProcess) {

		_portletImportInProcess.set(portletImportInProcess);
	}

	public static void setPortletValidationInProcess(
		boolean portletValidationInProcess) {

		_portletValidationInProcess.set(portletValidationInProcess);
	}

	private static ThreadLocal<Boolean> _layoutExportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ExportImportThreadLocal.class + "._layoutExportInProcess", false);
	private static ThreadLocal<Boolean> _layoutImportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ExportImportThreadLocal.class + "._layoutImportInProcess", false);
	private static ThreadLocal<Boolean> _layoutValidationInProcess =
		new AutoResetThreadLocal<Boolean>(
			ExportImportThreadLocal.class + "._layoutValidationInProcess",
			false);
	private static ThreadLocal<Boolean> _portletExportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ExportImportThreadLocal.class + "._portletExportInProcess", false);
	private static ThreadLocal<Boolean> _portletImportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ExportImportThreadLocal.class + "._portletImportInProcess", false);
	private static ThreadLocal<Boolean> _portletValidationInProcess =
		new AutoResetThreadLocal<Boolean>(
			ExportImportThreadLocal.class + "._portletValidationInProcess",
			false);

}