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

import java.util.List;

/**
 * The interface for the staged model data handler registry framework.
 *
 * @author Mate Thurzo
 * @see    com.liferay.portal.kernel.lar.StagedModelDataHandlerRegistryUtil
 * @since  6.2
 */
public interface StagedModelDataHandlerRegistry {

	public StagedModelDataHandler<?> getStagedModelDataHandler(
		String className);

	public List<StagedModelDataHandler<?>> getStagedModelDataHandlers();

	public void register(StagedModelDataHandler<?> stagedModelDataHandler);

	public void unregister(StagedModelDataHandler<?> stagedModelDataHandler);

}