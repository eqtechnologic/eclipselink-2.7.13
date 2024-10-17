/*
 * Copyright (c) 2021, 2023 Oracle, IBM Corporation, and/or their affiliates. All rights reserved.
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
//      Oracle - initial API and implementation
package org.eclipse.persistence.internal.libraries.asm;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * EclipseLink specific {@link ClassVisitor} that generates a corresponding ClassFile structure
 * for currently running Java VM.
 */
public class EclipseLinkASMClassWriter extends ClassWriter {

    private static final Logger LOG = Logger.getLogger(EclipseLinkASMClassWriter.class.getName());

    private static final int version = getLatestOPCodeVersion();

    public EclipseLinkASMClassWriter() {
        this(ClassWriter.COMPUTE_FRAMES);
    }

    public EclipseLinkASMClassWriter(final int flags) {
        super(flags);
    }

  /**
   * Visits the header of the class with {@code version} set
   * equal to currently running Java SE version.
   *
   * @param access the class's access flags (see {@link Opcodes}). This parameter also indicates if
   *     the class is deprecated {@link Opcodes#ACC_DEPRECATED} or a record {@link
   *     Opcodes#ACC_RECORD}.
   * @param name the internal name of the class (see {@link Type#getInternalName()}).
   * @param signature the signature of this class. May be {@literal null} if the class is not a
   *     generic one, and does not extend or implement generic classes or interfaces.
   * @param superName the internal of name of the super class (see {@link Type#getInternalName()}).
   *     For interfaces, the super class is {@link Object}. May be {@literal null}, but only for the
   *     {@link Object} class.
   * @param interfaces the internal names of the class's interfaces (see {@link
   *     Type#getInternalName()}). May be {@literal null}.
   * @see #visit(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
   */
    public final void visit(final int access, final String name,
            final String signature, final String superName, final String[] interfaces) {
        visit(version, access, name, signature, superName, interfaces);
    }

    private static int getLatestOPCodeVersion() {
        final Map<String, Integer> versionMap = new LinkedHashMap<>();
        Pattern searchPattern = Pattern.compile("^V\\d((_\\d)?|\\d*)");
        try {
            Class opcodesClazz = Opcodes.class;
            for (Field f : opcodesClazz.getDeclaredFields()) {
                if (searchPattern.matcher(f.getName()).matches()) {
                    versionMap.put(f.getName().replace("V","").replace('_', '.'), f.getInt(opcodesClazz));
                }
            }
        } catch (ReflectiveOperationException | SecurityException ex) {
            LOG.log(Level.SEVERE, "Error Java versions map from Opcodes.class fields.", ex);
            throw new RuntimeException(ex);
        }

        final List<String> versions = new ArrayList<String>(versionMap.keySet());
        final String oldest = versions.get(0);
        final String latest = versions.get(versions.size() - 1);

        // let's default to oldest supported Java SE version
        String v = oldest;
        if (System.getSecurityManager() == null) {
            v = System.getProperty("java.specification.version");
        } else {
            try {
                v = AccessController.doPrivileged(new PrivilegedAction<String>() {
                    @Override
                    public String run() {
                        return System.getProperty("java.specification.version");
                    }
                });
            } catch (Throwable t) {
                // ie SecurityException
                LOG.log(Level.WARNING, "Cannot read 'java.specification.version' property.", t);
                if (LOG.isLoggable(Level.FINE)) {
                    LOG.log(Level.FINE, "Generating bytecode for Java SE ''{0}''.", v);
                }
            }
        }
        Integer version = versionMap.get(v);
        if (version == null) {
            // current JDK is either too new
            if (latest.compareTo(v) < 0) {
                LOG.log(Level.WARNING, "Java SE ''{0}'' is not fully supported yet. Report this error to the EclipseLink open source project.", v);
                if (LOG.isLoggable(Level.FINE)) {
                    LOG.log(Level.FINE, "Generating bytecode for Java SE ''{0}''.", latest);
                }
                version = versionMap.get(latest);
            } else {
                // or too old
                String key = oldest;
                LOG.log(Level.WARNING, "Java SE ''{0}'' is too old.", v);
                if (LOG.isLoggable(Level.FINE)) {
                    LOG.log(Level.FINE, "Generating bytecode for Java SE ''{0}''.", key);
                }
                version = versionMap.get(key);
            }
        }
        return version;
    }
}
