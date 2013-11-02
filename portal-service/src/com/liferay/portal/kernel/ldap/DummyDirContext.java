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

package com.liferay.portal.kernel.ldap;

import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * @author Brian Wing Shun Chan
 */
public class DummyDirContext extends DummyContext implements DirContext {

	@Override
	public void bind(Name name, Object obj, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public void bind(String name, Object obj, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public DirContext createSubcontext(Name name, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public DirContext createSubcontext(String name, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public Attributes getAttributes(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	@Override
	public Attributes getAttributes(Name name, String[] attrIds)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public Attributes getAttributes(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	@Override
	public Attributes getAttributes(String name, String[] attrIds)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public DirContext getSchema(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	@Override
	public DirContext getSchema(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	@Override
	public DirContext getSchemaClassDefinition(Name name)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public DirContext getSchemaClassDefinition(String name)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public void modifyAttributes(
			Name name, int modificationOp, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public void modifyAttributes(Name name, ModificationItem[] mods)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public void modifyAttributes(
			String name, int modificationOp, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public void modifyAttributes(String name, ModificationItem[] mods)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public void rebind(Name name, Object obj, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public void rebind(String name, Object obj, Attributes attrs)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			Name name, Attributes matchingAttributes)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			Name name, Attributes matchingAttributes,
			String[] attributesToReturn)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			Name name, String filterExpr, Object[] filterArgs,
			SearchControls cons)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			Name name, String filter, SearchControls cons)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			String name, Attributes matchingAttributes)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			String name, Attributes matchingAttributes,
			String[] attributesToReturn)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			String name, String filterExpr, Object[] filterArgs,
			SearchControls cons)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

	@Override
	public NamingEnumeration<SearchResult> search(
			String name, String filter, SearchControls cons)
		throws NamingException {

		throw new OperationNotSupportedException();
	}

}