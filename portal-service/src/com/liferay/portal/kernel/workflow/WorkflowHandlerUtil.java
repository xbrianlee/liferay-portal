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

package com.liferay.portal.kernel.workflow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class WorkflowHandlerUtil {

	public static String[] getSearchableAssetTypes() {
		List<String> assetTypes = new ArrayList<String>();

		List<WorkflowHandler> workflowHandlers =
			WorkflowHandlerRegistryUtil.getWorkflowHandlers();

		for (WorkflowHandler workflowHandler : workflowHandlers) {
			if (!workflowHandler.isAssetTypeSearchable()) {
				continue;
			}

			assetTypes.add(workflowHandler.getClassName());
		}

		return assetTypes.toArray(new String[assetTypes.size()]);
	}

}