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

package com.liferay.portal.kernel.template;

import java.util.List;

/**
 * @author Juan Fernández
 */
public interface TemplateHandlerRegistry {

	public long[] getClassNameIds();

	/**
	 * Returns the template handler associated with the class name ID.
	 *
	 * @param  classNameId the class name ID of the template
	 * @return the template handler associated with the class name ID
	 */
	public TemplateHandler getTemplateHandler(long classNameId);

	/**
	 * Returns the template handler associated with the class name.
	 *
	 * @param  className the class name of the template
	 * @return the template handler associated with the class name
	 */
	public TemplateHandler getTemplateHandler(String className);

	/**
	 * Returns all the template handlers.
	 *
	 * @return the template handlers
	 */
	public List<TemplateHandler> getTemplateHandlers();

	/**
	 * Registers the template handler.
	 *
	 * @param templateHandler the template handler to register
	 */
	public void register(TemplateHandler templateHandler);

	/**
	 * Unregisters the template handler.
	 *
	 * @param templateHandler the template handler to unregister
	 */
	public void unregister(TemplateHandler templateHandler);

}