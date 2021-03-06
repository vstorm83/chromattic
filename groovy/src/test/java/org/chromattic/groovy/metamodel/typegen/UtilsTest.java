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

package org.chromattic.groovy.metamodel.typegen;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.chromattic.groovy.GroovyUtils;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class UtilsTest extends TestCase {
  public void testGetterName() {
    Assert.assertEquals(GroovyUtils.getsetName(GroovyUtils.GetSet.GET, "property"), "getProperty");
    Assert.assertEquals(GroovyUtils.getsetName(GroovyUtils.GetSet.SET, "property"), "setProperty");
  }

  public void testFieldName() {
    Assert.assertEquals("name", GroovyUtils.fieldName("getName"));
    Assert.assertEquals("name", GroovyUtils.fieldName("setName"));
  }

  public void testInvalidGetterSetter() {
    try {
      GroovyUtils.fieldName("etName");
      fail();
    } catch (Exception ok) {}
    try {
      GroovyUtils.fieldName("e");
      fail();
    } catch (Exception ok) {}
    try {
      GroovyUtils.fieldName(null);
      fail();
    } catch (Exception ok) {}
  }
}