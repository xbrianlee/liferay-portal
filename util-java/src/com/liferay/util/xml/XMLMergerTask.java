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

package com.liferay.util.xml;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Jorge Ferrer
 */
public class XMLMergerTask extends Task {

	@Override
	public void execute() throws BuildException {
		_validateAttributes();

		try {
			XMLMergerRunner runner = new XMLMergerRunner(_type);

			runner.mergeAndSave(_masterFile, _slaveFile, _outputFile);
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
	}

	public void setMasterFile(File masterFile) {
		_masterFile = masterFile;
	}

	public void setOutputFile(File outputFile) {
		_outputFile = outputFile;
	}

	public void setSlaveFile(File slaveFile) {
		_slaveFile = slaveFile;
	}

	public void setType(String type) {
		_type = type;
	}

	private void _validateAttributes() {
		_validateMandatoryAttribute(_masterFile, "masterFile");
		_validateMandatoryAttribute(_slaveFile, "slaveFile");
		_validateMandatoryAttribute(_outputFile, "outputFile");
	}

	private void _validateMandatoryAttribute(File value, String name) {
		if (value == null) {
			throw new BuildException(name + " is a required attribute");
		}
	}

	private File _masterFile;
	private File _outputFile;
	private File _slaveFile;
	private String _type;

}