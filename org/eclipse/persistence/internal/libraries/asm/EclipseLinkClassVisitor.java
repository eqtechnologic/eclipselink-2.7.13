/*
 * Copyright (c) 2021 Oracle, IBM Corporation, and/or their affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
// Contributors:
//      IBM - initial API and implementation

package org.eclipse.persistence.internal.libraries.asm;

public class EclipseLinkClassVisitor extends ClassVisitor {
	public EclipseLinkClassVisitor() {
		super(Opcodes.ASM9);
	}
	
	public EclipseLinkClassVisitor(ClassVisitor classVisitor) {
		super(Opcodes.ASM9, classVisitor);
	}
	
	public void visit(
			final int access,
			final String name,
			final String signature,
			final String superName,
			final String[] interfaces) {
		visit(Opcodes.ASM9, access, name, signature, superName, interfaces);

	}
}
