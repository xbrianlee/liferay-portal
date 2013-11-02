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

package com.liferay.portal.xml;

import com.liferay.portal.kernel.xml.ProcessingInstruction;
import com.liferay.portal.kernel.xml.Visitor;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ProcessingInstructionImpl
	extends NodeImpl implements ProcessingInstruction {

	public ProcessingInstructionImpl(
		org.dom4j.ProcessingInstruction processingInstruction) {

		super(processingInstruction);

		_processingInstruction = processingInstruction;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitProcessInstruction(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessingInstructionImpl)) {
			return false;
		}

		org.dom4j.ProcessingInstruction processingInstruction =
			((ProcessingInstructionImpl)obj).getWrappedProcessingInstruction();

		return _processingInstruction.equals(processingInstruction);
	}

	@Override
	public String getTarget() {
		return _processingInstruction.getTarget();
	}

	@Override
	public String getText() {
		return _processingInstruction.getText();
	}

	@Override
	public String getValue(String name) {
		return _processingInstruction.getValue(name);
	}

	@Override
	public Map<String, String> getValues() {
		return _processingInstruction.getValues();
	}

	public org.dom4j.ProcessingInstruction getWrappedProcessingInstruction() {
		return _processingInstruction;
	}

	@Override
	public int hashCode() {
		return _processingInstruction.hashCode();
	}

	@Override
	public boolean removeValue(String name) {
		return _processingInstruction.removeValue(name);
	}

	@Override
	public void setTarget(String target) {
		_processingInstruction.setTarget(target);
	}

	@Override
	public void setValue(String name, String value) {
		_processingInstruction.setValue(name, value);
	}

	@Override
	public void setValues(Map<String, String> data) {
		_processingInstruction.setValues(data);
	}

	@Override
	public String toString() {
		return _processingInstruction.toString();
	}

	private org.dom4j.ProcessingInstruction _processingInstruction;

}