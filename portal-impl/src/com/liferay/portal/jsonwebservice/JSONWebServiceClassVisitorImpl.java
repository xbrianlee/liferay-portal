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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceClassVisitor;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author Igor Spasic
 * @author Raymond Augé
 */
public class JSONWebServiceClassVisitorImpl
	implements ClassVisitor, JSONWebServiceClassVisitor {

	public JSONWebServiceClassVisitorImpl(InputStream inputStream)
		throws IOException {

		_classReader = new ClassReader(inputStream);
	}

	@Override
	public void accept() throws Exception {
		_classReader.accept(this, 0);
	}

	@Override
	public String getClassName() {
		return _className;
	}

	@Override
	public void visit(
		int version, int access, String name, String signature,
		String superName, String[] interfaces) {

		_className = StringUtil.replace(name, CharPool.SLASH, CharPool.PERIOD);
	}

	@Override
	public AnnotationVisitor visitAnnotation(
		String description, boolean visible) {

		return null;
	}

	@Override
	public void visitAttribute(Attribute attribute) {
	}

	@Override
	public void visitEnd() {
	}

	@Override
	public FieldVisitor visitField(
		int access, String name, String description, String signature,
		Object value) {

		return null;
	}

	@Override
	public void visitInnerClass(
		String name, String outerName, String innerName, int access) {
	}

	@Override
	public MethodVisitor visitMethod(
		int access, String name, String description, String signature,
		String[] exceptions) {

		return null;
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
	}

	@Override
	public void visitSource(String source, String debug) {
	}

	private String _className;
	private ClassReader _classReader;

}