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

package com.liferay.portal.editor.fckeditor.command.impl;

import com.liferay.portal.editor.fckeditor.command.Command;
import com.liferay.portal.editor.fckeditor.command.CommandArgument;
import com.liferay.portal.editor.fckeditor.receiver.CommandReceiver;
import com.liferay.portal.editor.fckeditor.receiver.CommandReceiverFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 */
public class GetFoldersAndFilesCommand implements Command {

	@Override
	public void execute(
		CommandArgument commandArgument, HttpServletRequest request,
		HttpServletResponse response) {

		CommandReceiver commandReceiver =
			CommandReceiverFactory.getCommandReceiver(
				commandArgument.getType());

		commandReceiver.getFoldersAndFiles(commandArgument, request, response);
	}

}