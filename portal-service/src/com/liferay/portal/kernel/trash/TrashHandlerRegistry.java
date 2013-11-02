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

package com.liferay.portal.kernel.trash;

import java.util.List;

/**
 * Represents the interface for registering handlers for those entities that can
 * be moved to Trash.
 *
 * <p>
 * The entities that can be registered are:
 * </p>
 *
 * <ul>
 * <li>
 * {@link com.liferay.portlet.blogs.trash.BlogsEntryTrashHandler}
 * </li>
 * </ul>
 *
 * @author Alexander Chow
 */
public interface TrashHandlerRegistry {

	/**
	 * Returns the trash handler associated with the class name.
	 *
	 * @param  className class name of the TrashHandler
	 * @return the trash handler associated with the class name
	 */
	public TrashHandler getTrashHandler(String className);

	/**
	 * Returns all of the trash handlers.
	 *
	 * @return the trash handlers
	 */
	public List<TrashHandler> getTrashHandlers();

	/**
	 * Registers the trash handler.
	 *
	 * @param trashHandler the TrashHandler to register
	 */
	public void register(TrashHandler trashHandler);

	/**
	 * Unregisters the trash handler.
	 *
	 * @param trashHandler the trash handler to unregister
	 */
	public void unregister(TrashHandler trashHandler);

}