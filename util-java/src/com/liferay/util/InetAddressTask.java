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

package com.liferay.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Brian Wing Shun Chan
 */
public class InetAddressTask extends Task {

	@Override
	public void execute() throws BuildException {
		try {
			InetAddress localHost = InetAddress.getLocalHost();

			if (Validator.isNotNull(_hostAddressProperty)) {
				getProject().setUserProperty(
					_hostAddressProperty, localHost.getHostAddress());
			}

			if (Validator.isNotNull(_hostNameProperty)) {
				getProject().setUserProperty(
					_hostNameProperty, localHost.getHostName());
			}

			if (Validator.isNotNull(_vmId1Property)) {
				int id = GetterUtil.getInteger(
					StringUtil.extractDigits(localHost.getHostName()));

				getProject().setUserProperty(
					_vmId1Property, String.valueOf((id * 2) - 1));
			}

			if (Validator.isNotNull(_vmId2Property)) {
				int id = GetterUtil.getInteger(
					StringUtil.extractDigits(localHost.getHostName()));

				getProject().setUserProperty(
					_vmId2Property, String.valueOf((id * 2)));
			}
		}
		catch (UnknownHostException uhe) {
			throw new BuildException(uhe);
		}
	}

	public void setHostAddressProperty(String hostAddressProperty) {
		_hostAddressProperty = hostAddressProperty;
	}

	public void setHostNameProperty(String hostNameProperty) {
		_hostNameProperty = hostNameProperty;
	}

	public void setVmId1Property(String vmId1Property) {
		_vmId1Property = vmId1Property;
	}

	public void setVmId2Property(String vmId2Property) {
		_vmId2Property = vmId2Property;
	}

	private String _hostAddressProperty;
	private String _hostNameProperty;
	private String _vmId1Property;
	private String _vmId2Property;

}