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

package com.liferay.portal.image;

import com.liferay.portal.kernel.image.Ghostscript;
import com.liferay.portal.kernel.image.ImageMagickUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.process.ProcessUtil;
import com.liferay.portal.kernel.util.OSDetector;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Ivica Cardic
 */
public class GhostscriptImpl implements Ghostscript {

	@Override
	public Future<?> execute(List<String> commandArguments) throws Exception {
		if (!isEnabled()) {
			StringBundler sb = new StringBundler(6);

			sb.append("Cannot execute the Ghostscript command. Please ");
			sb.append("install ImageMagick and Ghostscript and enable ");
			sb.append("ImageMagick in portal-ext.properties or in the Server ");
			sb.append("Administration section of the Control Panel at: ");
			sb.append("http://<server>/group/control_panel/manage/-/server/");
			sb.append("external-services");

			throw new IllegalStateException(sb.toString());
		}

		LinkedList<String> arguments = new LinkedList<String>();

		arguments.add(_commandPath);
		arguments.add("-dBATCH");
		arguments.add("-dSAFER");
		arguments.add("-dNOPAUSE");
		arguments.add("-dNOPROMPT");
		arguments.add("-sFONTPATH" + _globalSearchPath);
		arguments.addAll(commandArguments);

		if (_log.isInfoEnabled()) {
			StringBundler sb = new StringBundler(arguments.size() * 2);

			for (String argument : arguments) {
				sb.append(argument);
				sb.append(StringPool.SPACE);
			}

			_log.info("Excecuting command '" + sb.toString() + "'");
		}

		return ProcessUtil.execute(
			ProcessUtil.LOGGING_OUTPUT_PROCESSOR, arguments);
	}

	@Override
	public boolean isEnabled() {
		return ImageMagickUtil.isEnabled();
	}

	@Override
	public void reset() {
		if (isEnabled()) {
			try {
				_globalSearchPath = ImageMagickUtil.getGlobalSearchPath();

				_commandPath = getCommandPath();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	protected String getCommandPath() throws Exception {
		String commandPath = null;

		if (OSDetector.isWindows()) {
			commandPath = getCommandPathWindows();
		}
		else {
			commandPath = getCommandPathUnix();
		}

		if (commandPath == null) {
			StringBundler sb = new StringBundler(4);

			sb.append("Unable to find the Ghostscript command. Please verify ");
			sb.append("the path specified in the Server Administration ");
			sb.append("control panel at: http://<server>/group/control_panel/");
			sb.append("manage/-/server/external-services");

			throw new FileNotFoundException(sb.toString());
		}

		return commandPath;
	}

	protected String getCommandPathUnix() throws Exception {
		String[] dirNames = _globalSearchPath.split(File.pathSeparator);

		for (String dirName : dirNames) {
			File file = new File(dirName, _GHOSTSCRIPT_COMMAND_UNIX);

			if (file.exists()) {
				return file.getCanonicalPath();
			}
		}

		return null;
	}

	protected String getCommandPathWindows() throws Exception {
		String[] dirNames = _globalSearchPath.split(File.pathSeparator);

		for (String dirName : dirNames) {
			for (String command : _GHOSTSCRIPT_COMMAND_WINDOWS) {
				File file = new File(dirName, command + ".exe");

				if (!file.exists()) {
					file = new File(dirName, command + ".cmd");

					if (!file.exists()) {
						file = new File(dirName, command + ".bat");

						if (!file.exists()) {
							continue;
						}
					}
				}

				return file.getCanonicalPath();
			}
		}

		return null;
	}

	private static final String _GHOSTSCRIPT_COMMAND_UNIX = "gs";

	private static final String[] _GHOSTSCRIPT_COMMAND_WINDOWS = {
		"gswin32c", "gswin64c"
	};

	private static Log _log = LogFactoryUtil.getLog(GhostscriptImpl.class);

	private String _commandPath;
	private String _globalSearchPath;

}