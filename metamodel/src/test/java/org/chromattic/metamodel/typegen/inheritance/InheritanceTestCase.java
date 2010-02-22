/*
 * Copyright (C) 2003-2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.chromattic.metamodel.typegen.inheritance;

import junit.framework.TestCase;
import org.chromattic.metamodel.typegen.NodeType;
import org.chromattic.metamodel.typegen.TypeGen;
import org.reflext.api.ClassTypeInfo;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class InheritanceTestCase extends TestCase {

  public void testProperty() throws Exception {
    TypeGen gen = new TypeGen();
    ClassTypeInfo a1 = gen.addType(A1.class);
    ClassTypeInfo a3 = gen.addType(A3.class);
    gen.generate();
    NodeType a1NT = gen.getNodeType(a1);
    assertEquals(1, a1NT.getPropertyDefinitions().size());
    NodeType a3NT = gen.getNodeType(a3);
    assertEquals(0, a3NT.getPropertyDefinitions().size());
  }

  public void testOneToOne() throws Exception {
    TypeGen gen = new TypeGen();
    ClassTypeInfo a1 = gen.addType(A1.class);
    ClassTypeInfo a3 = gen.addType(A3.class);
    gen.generate();
    NodeType a1NT = gen.getNodeType(a1);
    assertEquals(1, a1NT.getChildNodeDefinitions().size());
    NodeType a3NT = gen.getNodeType(a3);
    assertEquals(0, a3NT.getChildNodeDefinitions().size());
  }
}