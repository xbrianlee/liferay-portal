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

package com.liferay.portal.kernel.javadoc;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JavadocMethod extends BaseJavadoc {

	public JavadocMethod(Method method) {
		this(method, null);
	}

	public JavadocMethod(Method method, String comment) {
		_method = method;

		setComment(comment);
	}

	public Method getMethod() {
		return _method;
	}

	public String getParameterComment(int index) {
		if (_parameterComments == null) {
			return null;
		}

		return _parameterComments[index];
	}

	public String[] getParameterComments() {
		return _parameterComments;
	}

	public String getReturnComment() {
		return _returnComment;
	}

	public String getThrowsComment(int index) {
		if (_throwsComments == null) {
			return null;
		}

		return _throwsComments[index];
	}

	public String[] getThrowsComments() {
		return _throwsComments;
	}

	public void setMethod(Method method) {
		_method = method;
	}

	public void setParameterComments(String[] parameterComments) {
		_parameterComments = parameterComments;
	}

	public void setReturnComment(String returnComment) {
		_returnComment = returnComment;
	}

	public void setThrowsComments(String[] throwsComments) {
		_throwsComments = throwsComments;
	}

	private Method _method;
	private String[] _parameterComments;
	private String _returnComment;
	private String[] _throwsComments;

}