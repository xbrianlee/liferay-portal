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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;

/**
 * @author Juan Fernández
 */
public class AddDefaultDataAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		SimpleAction addDefaultDocumentLibraryStructuresAction =
			new AddDefaultDocumentLibraryStructuresAction();

		addDefaultDocumentLibraryStructuresAction.run(ids);

		SimpleAction addDefaultLayoutPrototypesAction =
			new AddDefaultLayoutPrototypesAction();

		addDefaultLayoutPrototypesAction.run(ids);

		SimpleAction addDefaultLayoutSetPrototypesAction =
			new AddDefaultLayoutSetPrototypesAction();

		addDefaultLayoutSetPrototypesAction.run(ids);

		SimpleAction addDefaultDDMStructuresAction =
			new AddDefaultDDMStructuresAction();

		addDefaultDDMStructuresAction.run(ids);

		SimpleAction addDefaultDDMTemplatesAction =
			new AddDefaultDDMTemplatesAction();

		addDefaultDDMTemplatesAction.run(ids);
	}

}