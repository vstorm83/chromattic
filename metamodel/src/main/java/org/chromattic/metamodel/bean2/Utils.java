/*
 * Copyright (C) 2010 eXo Platform SAS.
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

package org.chromattic.metamodel.bean2;

import org.reflext.api.ClassTypeInfo;
import org.reflext.api.TypeInfo;
import org.reflext.api.TypeVariableInfo;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
class Utils {

  /*
   * todo: defines clearly what this does. 
   */
  static ClassTypeInfo resolveToClassType(ClassTypeInfo baseType, TypeInfo type) {
    TypeInfo resolvedType = baseType.resolve(type);

    //
    if (resolvedType instanceof ClassTypeInfo) {
      return (ClassTypeInfo)resolvedType;
    } else if (resolvedType instanceof TypeVariableInfo) {
      return resolveToClassType(baseType, ((TypeVariableInfo)resolvedType).getBounds().get(0));
    } else {
      return null;
    }
  }
}