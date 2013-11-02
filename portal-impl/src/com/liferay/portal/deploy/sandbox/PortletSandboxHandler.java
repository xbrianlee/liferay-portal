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

package com.liferay.portal.deploy.sandbox;

import com.liferay.portal.kernel.deploy.Deployer;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 */
public class PortletSandboxHandler extends BaseSandboxHandler {

	public PortletSandboxHandler(Deployer deployer) {
		super(deployer);

		_deployer = deployer;
	}

	@Override
	protected void clonePlugin(File dir, PluginPackage pluginPackage)
		throws Exception {

		Map<String, String> filterMap = new HashMap<String, String>();

		filterMap.put(
			"portlet_class", "com.liferay.util.bridges.alloy.AlloyPortlet");
		filterMap.put("portlet_name", "1");
		filterMap.put("portlet_title", pluginPackage.getName());
		filterMap.put("restore_current_view", "false");
		filterMap.put(
			"friendly_url_mapper_class",
			"com.liferay.util.bridges.alloy.AlloyFriendlyURLMapper");
		filterMap.put(
			"friendly_url_mapping",
			TextFormatter.format(pluginPackage.getName(), TextFormatter.B));
		filterMap.put(
			"friendly_url_routes",
			"com/liferay/util/bridges/alloy/alloy-friendly-url-routes.xml");
		filterMap.put(
			"namespace",
			TextFormatter.format(pluginPackage.getName(), TextFormatter.D));
		filterMap.put(
			"package_path",
			"com.liferay." +
				TextFormatter.format(pluginPackage.getName(), TextFormatter.B));

		_deployer.copyDependencyXml(
			"liferay-display.xml", dir + "/WEB-INF", filterMap);

		updateLiferayDisplayXML(dir);

		_deployer.copyDependencyXml(
			"liferay-portlet.xml", dir + "/WEB-INF", filterMap);

		_deployer.copyDependencyXml("portlet.xml", dir + "/WEB-INF", filterMap);

		updatePortletXML(dir);

		_deployer.copyDependencyXml("service.xml", dir + "/WEB-INF", filterMap);
	}

	@Override
	protected String getPluginType() {
		return _PLUGIN_TYPE;
	}

	protected Namespace getPortletNamespace(Document document) {
		Element rootElement = document.getRootElement();

		QName qName = rootElement.getQName();

		return qName.getNamespace();
	}

	protected void updateLiferayDisplayXML(File dir) throws Exception {
		File file = new File(dir + "/WEB-INF/liferay-display.xml");

		String content = FileUtil.read(file);

		Document document = SAXReaderUtil.read(content);

		Element rootElement = document.getRootElement();

		Element categoryElement = rootElement.addElement("category");

		categoryElement.addAttribute("name", "category.sandbox");

		Element portletElement = categoryElement.addElement("portlet");

		portletElement.addAttribute("id", "1");

		FileUtil.write(file, document.formattedString());
	}

	protected void updatePortletXML(File dir) throws Exception {
		File file = new File(dir + "/WEB-INF/portlet.xml");

		String content = FileUtil.read(file);

		Document document = SAXReaderUtil.read(content);

		Element rootElement = document.getRootElement();

		List<Element> portletElements = rootElement.elements("portlet");

		for (Element portletElement : portletElements) {
			List<Element> elements = new ArrayList<Element>();

			Element resourceBundleElement = SAXReaderUtil.createElement(
				SAXReaderUtil.createQName(
					"resource-bundle", getPortletNamespace(document)));

			resourceBundleElement.setText("content.Language");

			for (Element element : portletElement.elements()) {
				String elementName = element.getName();

				if (elementName.equals("init-param") ||
					elementName.equals("portlet-info")) {
				}
				else {
					elements.add(element);

					if (elementName.equals("supports")) {
						elements.add(resourceBundleElement);
					}
				}

				element.detach();
			}

			for (Element element : elements) {
				portletElement.add(element);
			}
		}

		FileUtil.write(file, document.formattedString());
	}

	private static final String _PLUGIN_TYPE = "portlet";

	private Deployer _deployer;

}