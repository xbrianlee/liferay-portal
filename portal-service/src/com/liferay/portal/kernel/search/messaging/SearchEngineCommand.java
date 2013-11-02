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

package com.liferay.portal.kernel.search.messaging;

/**
 * @author Michael C. Han
 */
public enum SearchEngineCommand {

	ADD_DOCUMENT, ADD_DOCUMENTS, DELETE_DOCUMENT, DELETE_DOCUMENTS,
	DELETE_PORTLET_DOCUMENTS, SEARCH, UPDATE_DOCUMENT, UPDATE_DOCUMENTS

}