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

package com.liferay.portal.sharepoint.methods;

import com.liferay.portal.sharepoint.Leaf;
import com.liferay.portal.sharepoint.Property;
import com.liferay.portal.sharepoint.ResponseElement;
import com.liferay.portal.sharepoint.SharepointRequest;
import com.liferay.portal.sharepoint.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class OpenServiceMethodImpl extends BaseMethodImpl {

	public OpenServiceMethodImpl() {
		Tree metaInfoTree = new Tree();

		metaInfoTree.addChild(new Leaf("vti_casesensitiveurls", "IX|0", false));
		metaInfoTree.addChild(new Leaf("vti_longfilenames", "IX|1", false));
		metaInfoTree.addChild(
			new Leaf("vti_welcomenames", "VX|index.html", false));
		metaInfoTree.addChild(new Leaf("vti_username", "SX|joebloggs", false));
		metaInfoTree.addChild(new Leaf("vti_servertz", "SX|-0700", false));
		metaInfoTree.addChild(
			new Leaf("vti_sourcecontrolsystem", "SR|lw", false));
		metaInfoTree.addChild(
			new Leaf("vti_sourcecontrolversion", "SR|V1", false));
		metaInfoTree.addChild(
			new Leaf("vti_doclibwebviewenabled", "IX|0", false));
		metaInfoTree.addChild(
			new Leaf("vti_sourcecontrolcookie", "SX|fp_internal", false));
		metaInfoTree.addChild(
			new Leaf(
				"vti_sourcecontrolproject", "SX|&#60;STS-based Locking&#62;",
				false));

		Tree serviceTree = new Tree();

		serviceTree.addChild(new Leaf("service_name", "/sharepoint", true));
		serviceTree.addChild(new Leaf("meta_info", metaInfoTree));

		Property serviceProperty = new Property("service", serviceTree);

		_elements.add(serviceProperty);
	}

	@Override
	public String getMethodName() {
		return _METHOD_NAME;
	}

	@Override
	protected List<ResponseElement> getElements(
		SharepointRequest sharepointRequest) {

		return _elements;
	}

	private static final String _METHOD_NAME = "open service";

	private List<ResponseElement> _elements = new ArrayList<ResponseElement>();

}