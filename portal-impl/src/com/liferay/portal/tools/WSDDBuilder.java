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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.tools.servicebuilder.ServiceBuilder;
import com.liferay.portal.util.InitUtil;
import com.liferay.util.ant.Java2WsddTask;

import java.io.File;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class WSDDBuilder {

	public static void main(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		InitUtil.initWithSpring();

		WSDDBuilder wsddBuilder = new WSDDBuilder();

		wsddBuilder._fileName = arguments.get("wsdd.input.file");
		wsddBuilder._outputPath = arguments.get("wsdd.output.path");
		wsddBuilder._serverConfigFileName = arguments.get(
			"wsdd.server.config.file");
		wsddBuilder._serviceNamespace = arguments.get("wsdd.service.namespace");

		wsddBuilder.build();
	}

	public void build() throws Exception {
		if (!FileUtil.exists(_serverConfigFileName)) {
			ClassLoader classLoader = getClass().getClassLoader();

			String serverConfigContent = StringUtil.read(
				classLoader,
				"com/liferay/portal/tools/dependencies/server-config.wsdd");

			FileUtil.write(_serverConfigFileName, serverConfigContent);
		}

		String content = ServiceBuilder.getContent(_fileName);

		Document document = SAXReaderUtil.read(content, true);

		Element rootElement = document.getRootElement();

		String packagePath = rootElement.attributeValue("package-path");

		Element portletElement = rootElement.element("portlet");
		Element namespaceElement = rootElement.element("namespace");

		if (portletElement != null) {
			_portletShortName = portletElement.attributeValue("short-name");
		}
		else {
			_portletShortName = namespaceElement.getText();
		}

		_outputPath +=
			StringUtil.replace(packagePath, ".", "/") + "/service/http";

		_packagePath = packagePath;

		List<Element> entityElements = rootElement.elements("entity");

		for (Element entityElement : entityElements) {
			String entityName = entityElement.attributeValue("name");

			boolean remoteService = GetterUtil.getBoolean(
				entityElement.attributeValue("remote-service"), true);

			if (remoteService) {
				_createServiceWSDD(entityName);

				WSDDMerger.merge(
					_outputPath + "/" + entityName + "Service_deploy.wsdd",
					_serverConfigFileName);
			}
		}
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public void setOutputPath(String outputPath) {
		_outputPath = outputPath;
	}

	public void setServerConfigFileName(String serverConfigFileName) {
		_serverConfigFileName = serverConfigFileName;
	}

	public void setServiceNamespace(String serviceNamespace) {
		_serviceNamespace = serviceNamespace;
	}

	private void _createServiceWSDD(String entityName) throws Exception {
		String className =
			_packagePath + ".service.http." + entityName + "ServiceSoap";

		String serviceName = StringUtil.replace(_portletShortName, " ", "_");

		if (!_portletShortName.equals("Portal")) {
			serviceName = _serviceNamespace + "_" + serviceName;
		}

		serviceName += ("_" + entityName + "Service");

		String[] wsdds = Java2WsddTask.generateWsdd(className, serviceName);

		FileUtil.write(
			new File(_outputPath + "/" + entityName + "Service_deploy.wsdd"),
			wsdds[0], true);

		FileUtil.write(
			new File(_outputPath + "/" + entityName + "Service_undeploy.wsdd"),
			wsdds[1], true);
	}

	private String _fileName;
	private String _outputPath;
	private String _packagePath;
	private String _portletShortName;
	private String _serverConfigFileName;
	private String _serviceNamespace;

}