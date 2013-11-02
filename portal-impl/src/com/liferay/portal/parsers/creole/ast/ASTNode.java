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

package com.liferay.portal.parsers.creole.ast;

import com.liferay.portal.parsers.creole.visitor.ASTVisitor;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;

/**
 * @author Miguel Pastor
 */
public abstract class ASTNode {

	public ASTNode() {
	}

	public ASTNode(int tokenType) {
		this(new CommonToken(tokenType));
	}

	public ASTNode(Token token) {
		_token = token;
	}

	public abstract void accept(ASTVisitor astVisitor);

	public Token getToken() {
		return _token;
	}

	public void setToken(Token token) {
		_token = token;
	}

	private Token _token;

}